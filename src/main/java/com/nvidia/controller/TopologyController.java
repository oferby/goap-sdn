package com.nvidia.controller;

import com.nvidia.model.PortEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class TopologyController {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String HASH_KEY_NAME = "PORT-ITEM";

    public List<PortEntity> getAllPorts() {


        return (List<PortEntity>) redisTemplate.opsForHash().values(HASH_KEY_NAME);
    }

    public PortEntity findById(String UUID) {
        return (PortEntity) redisTemplate.opsForHash().get(HASH_KEY_NAME, UUID);
    }

    @PostConstruct
    private void setup() {

//        List<PortEntity> port_list = new ArrayList<>();

        PortEntity p = new PortEntity();
        p.setUUID("UUID1");
        p.setName("port1");
//        port_list.add(p);

        redisTemplate.opsForHash().put(HASH_KEY_NAME, p.getUUID(), p);


        p = new PortEntity();
        p.setUUID("UUID2");
        p.setName("port2");
//        port_list.add(p);

        redisTemplate.opsForHash().put(HASH_KEY_NAME, p.getUUID(), p);


    }

}
