package com.nvidia.web;

import com.nvidia.controller.PartitionEntityController;
import com.nvidia.model.PartitionEntity;
import com.nvidia.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PartitionWebController {

    @Autowired
    private PartitionEntityController partitionEntityController;


    @GetMapping("/partition")
    public List<PartitionEntity> getAll() {
        return partitionEntityController.getAllPartitions();
    }

    @PostMapping(value = "/partition", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PartitionEntity> createPartition(@RequestBody ArrayList<String> nodeUUIDList) {

        return partitionEntityController.create(nodeUUIDList);


    }

    @DeleteMapping("/partition/{uuid}")
    public ResponseEntity<PartitionEntity> deleteById(@PathVariable String uuid) {

        Status deleted = partitionEntityController.delete(uuid);

        if( deleted == Status.OK)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/partition")
    public ResponseEntity<PartitionEntity> deleteAll() {

        for (PartitionEntity partition : partitionEntityController.getAllPartitions()) {
            partitionEntityController.delete(partition.getUUID());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
