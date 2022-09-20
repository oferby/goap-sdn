package com.nvidia.controller;

import com.nvidia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TopologyController {

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


    @PostConstruct
    private void setup() {

        for (PortEntity portEntity : getAllPorts()) {
            deletePortById(portEntity.getUUID());
        }

        for (NodeEntity ne : getAllNodes()) {
            deleteNodeById(ne.getUUID());
        }

        List<PortEntity> portEntities = new ArrayList<>();

        PortEntity p = new PortEntity();
        p.setUUID(generateUUID());
        p.setName("port1");
        p.setStatus(OperationStatus.UP);

        portEntities.add(p);
        redisTemplate.opsForHash().put(HASH_PORT_KEY, p.getUUID(), p);

        p = new PortEntity();
        p.setUUID(generateUUID());
        p.setName("port2");
        p.setStatus(OperationStatus.UP);

        redisTemplate.opsForHash().put(HASH_PORT_KEY, p.getUUID(), p);
        portEntities.add(p);

        NodeEntity ne = new NodeEntity();
        ne.setUUID(generateUUID());
        ne.setName("Node1");
        ne.setNodeType(NodeType.GPU);

        List<String> portUUIDList = new ArrayList<>();

        for (PortEntity portEntity : portEntities) {
            portUUIDList.add(portEntity.getUUID());
        }

        ne.setPortEntities(portUUIDList);

        redisTemplate.opsForHash().put(HASH_NODE_KEY, ne.getUUID(), ne);

    }

    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }

}
