package com.nvidia.model;

public enum SubnetManagerStatus {
    UnConfigured,
    PartitionConfigOk,
    PendingPartitionConfig,
    PendingBasicConfig,
    BasicConfigOK,
    PendingReroute,
    RerouteFailed,
    Isolated
}
