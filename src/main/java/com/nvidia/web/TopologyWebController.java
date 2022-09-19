package com.nvidia.web;

import com.nvidia.controller.TopologyController;
import com.nvidia.model.PortEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopologyWebController {

    @Autowired
    TopologyController topologyController;

    @GetMapping("/topology/port")
    public List<PortEntity> getAllPorts() {
        return topologyController.getAllPorts();

    }

    @GetMapping("/topology/port/{uuid}")
    public PortEntity getPortById(@PathVariable String uuid) {

        return topologyController.findById(uuid);
    }


}
