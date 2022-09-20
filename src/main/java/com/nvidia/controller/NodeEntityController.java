package com.nvidia.controller;

import com.nvidia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.nvidia.controller.UUIDGenerator.generateUUID;

@Repository
public class NodeEntityController {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String HASH_PORT_KEY = "PORT-ITEM";
    public static final String HASH_NODE_KEY = "NODE-ITEM";

    public List<PortEntity> getAllPorts() {


        return (List<PortEntity>) redisTemplate.opsForHash().values(HASH_PORT_KEY);
    }

    public PortEntity findPortById(String UUID) {
        return (PortEntity) redisTemplate.opsForHash().get(HASH_PORT_KEY, UUID);
    }

    public Status deletePortById(String UUID) {
        Long delete = redisTemplate.opsForHash().delete(HASH_PORT_KEY, UUID);
        if (delete > 0)
            return Status.OK;

        return Status.NON_FATAL_ERROR;
    }

    public List<NodeEntity> getAllNodes() {
        return (List<NodeEntity>) redisTemplate.opsForHash().values(HASH_NODE_KEY);
    }

    public Status deleteNodeById(String UUID) {
        Long deleted = redisTemplate.opsForHash().delete(HASH_NODE_KEY, UUID);

        if (deleted > 0)
            return Status.OK;

        return Status.NON_FATAL_ERROR;
    }

    public NodeEntity findNodeById(String UUID) {
        return (NodeEntity) redisTemplate.opsForHash().get(HASH_NODE_KEY, UUID);
    }




//    @PostConstruct
    private void setup() {

        for (PortEntity portEntity : getAllPorts()) {
            deletePortById(portEntity.getUUID());
        }

        for (NodeEntity ne : getAllNodes()) {
            deleteNodeById(ne.getUUID());
        }

        for (int i = 0; i < 32; i++) {

            NodeEntity ne = new NodeEntity();
            ne.setUUID(generateUUID());
            ne.setName("Node" + i);
            ne.setNodeType(NodeType.GPU);
            ne.setPartitionManagerStatus(PartitionManagerStatus.NotReady);
            ne.setFabricManagerStatus(FabricManagerStatus.UnConfigured);
            ne.setSubnetManagerStatus(SubnetManagerStatus.UnConfigured);
            ne.setNodeNetworkStatus(NodeNetworkStatus.OK);
            ne.setNodeFabricStatus(NodeFabricStatus.OK);

            List<String> portUUIDList = new ArrayList<>();
            ne.setPortEntities(portUUIDList);


            for (int j = 0; j < 18; j++) {

                PortEntity p = new PortEntity();
                p.setUUID(generateUUID());
                p.setName("node-" + i + "-port-" + j);
                p.setStatus(OperationStatus.DOWN);

                portUUIDList.add(p.getUUID());

                redisTemplate.opsForHash().put(HASH_PORT_KEY, p.getUUID(), p);

            }

            redisTemplate.opsForHash().put(HASH_NODE_KEY, ne.getUUID(), ne);


        }


    }


}
