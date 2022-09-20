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
@NoArgsConstructor
@RedisHash("Node")
public class NodeEntity extends AbstractEntity implements Serializable {

    @Id
    private String UUID;
    private String name;
    private NodeType nodeType;
    private PartitionManagerStatus partitionManagerStatus;
    private SubnetManagerStatus subnetManagerStatus;
    private FabricManagerStatus fabricManagerStatus;
    private NodeNetworkStatus nodeNetworkStatus;
    private NodeFabricStatus nodeFabricStatus;
    private List<String> portEntities;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public PartitionManagerStatus getPartitionManagerStatus() {
        return partitionManagerStatus;
    }

    public void setPartitionManagerStatus(PartitionManagerStatus partitionManagerStatus) {
        this.partitionManagerStatus = partitionManagerStatus;
    }

    public SubnetManagerStatus getSubnetManagerStatus() {
        return subnetManagerStatus;
    }

    public void setSubnetManagerStatus(SubnetManagerStatus subnetManagerStatus) {
        this.subnetManagerStatus = subnetManagerStatus;
    }

    public FabricManagerStatus getFabricManagerStatus() {
        return fabricManagerStatus;
    }

    public void setFabricManagerStatus(FabricManagerStatus fabricManagerStatus) {
        this.fabricManagerStatus = fabricManagerStatus;
    }

    public NodeNetworkStatus getNodeNetworkStatus() {
        return nodeNetworkStatus;
    }

    public void setNodeNetworkStatus(NodeNetworkStatus nodeNetworkStatus) {
        this.nodeNetworkStatus = nodeNetworkStatus;
    }

    public NodeFabricStatus getNodeFabricStatus() {
        return nodeFabricStatus;
    }

    public void setNodeFabricStatus(NodeFabricStatus nodeFabricStatus) {
        this.nodeFabricStatus = nodeFabricStatus;
    }

    public List<String> getPortEntities() {
        return portEntities;
    }

    public void setPortEntities(List<String> portEntities) {
        this.portEntities = portEntities;
    }


}
