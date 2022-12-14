package com.nvidia.web;

import com.nvidia.controller.NodeEntityController;
import com.nvidia.model.NodeEntity;
import com.nvidia.model.PortEntity;
import com.nvidia.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopologyWebController {

    @Autowired
    NodeEntityController nodeEntityController;

    @GetMapping("/topology/port")
    public List<PortEntity> getAllPorts() {
        return nodeEntityController.getAllPorts();

    }

    @GetMapping("/topology/port/{uuid}")
    public PortEntity getPortById(@PathVariable String uuid) {

        return nodeEntityController.findPortById(uuid);
    }

    @DeleteMapping("/topology/port/{uuid}")
    public Status deletePortById(@PathVariable String uuid) {
        return nodeEntityController.deletePortById(uuid);
    }

    @GetMapping("/topology/node")
    List<NodeEntity> getAllNodes() {
        return nodeEntityController.getAllNodes();
    }



}
