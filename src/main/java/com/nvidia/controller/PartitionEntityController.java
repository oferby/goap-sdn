package com.nvidia.controller;


import com.nvidia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Repository
public class PartitionEntityController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private NodeEntityController nodeEntityController;

    public static final String HASH_PARTITION_KEY = "PARTITIONS";
    public static final String HASH_P_KEY = "P-KEY";

    public List<PartitionEntity> getAllPartitions() {
        return (List<PartitionEntity>) redisTemplate.opsForHash().values(HASH_PARTITION_KEY);
    }

    public ResponseEntity<PartitionEntity> create(List<String> nodeList) {

        if (!validateGpuNode(nodeList)) {
            return new ResponseEntity<PartitionEntity>(HttpStatus.BAD_REQUEST);
        }

        PartitionEntity partitionEntity = new PartitionEntity();
        partitionEntity.setUUID(UUIDGenerator.generateUUID());
        partitionEntity.setPartitionManagerStatus(PartitionManagerStatus.PendingCreate);
        partitionEntity.setP_key(getPKey());
        partitionEntity.setNodeUUIDList(nodeList);

        partitionEntity.setLink0DownPolicy(Link0DownPolicy.ReRoute);

        redisTemplate.opsForHash().put(HASH_PARTITION_KEY, partitionEntity.getUUID(), partitionEntity);

        return new ResponseEntity<PartitionEntity>(partitionEntity, HttpStatus.OK);
    }

    public Status delete(String uuid) {
        Long deleted = redisTemplate.opsForHash().delete(HASH_PARTITION_KEY, uuid);

        if (deleted > 0)
            return Status.OK;

        return Status.INPUT_ERROR;
    }

    private boolean validateGpuNode(List<String> nodeList) {

        for (String uuid : nodeList) {
            NodeEntity nodeEntity = nodeEntityController.findNodeById(uuid);

            if (nodeEntity == null)
                return false;

            if (nodeEntity.getNodeType() != NodeType.GPU)
                return false;

            if (nodeEntity.getPartitionManagerStatus() != PartitionManagerStatus.Ready)
                return false;

        }

        return true;
    }

    private int getPKey() {

        for (int i = 0; i < 65536; i++) {
            if (!redisTemplate.opsForSet().isMember(HASH_P_KEY, i)) {
                redisTemplate.opsForSet().add(HASH_P_KEY, i);
                return i;
            }
        }

        return -1;

    }

}
