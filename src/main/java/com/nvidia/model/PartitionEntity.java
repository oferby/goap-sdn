package com.nvidia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@RedisHash("Partition")
public class PartitionEntity extends AbstractEntity implements Serializable {

    @Id
    private String UUID;
    private int p_key;
    private List<String> nodeUUIDList;
    private PartitionManagerStatus partitionManagerStatus;
    private Link0DownPolicy link0DownPolicy;

    public PartitionEntity() {
    }

    public PartitionEntity(List<String> nodeUUIDList) {
        this.nodeUUIDList = nodeUUIDList;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getP_key() {
        return p_key;
    }

    public void setP_key(int p_key) {
        this.p_key = p_key;
    }

    public List<String> getNodeUUIDList() {
        return nodeUUIDList;
    }

    public void setNodeUUIDList(List<String> nodeUUIDList) {
        this.nodeUUIDList = nodeUUIDList;
    }

    public PartitionManagerStatus getPartitionManagerStatus() {
        return partitionManagerStatus;
    }

    public void setPartitionManagerStatus(PartitionManagerStatus partitionManagerStatus) {
        this.partitionManagerStatus = partitionManagerStatus;
    }

    public Link0DownPolicy getLink0DownPolicy() {
        return link0DownPolicy;
    }

    public void setLink0DownPolicy(Link0DownPolicy link0DownPolicy) {
        this.link0DownPolicy = link0DownPolicy;
    }
}
