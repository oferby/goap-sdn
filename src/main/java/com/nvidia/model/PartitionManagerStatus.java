package com.nvidia.model;

public enum PartitionManagerStatus {
    NotReady,
    Ready,
    PendingCreate,
    PendingDelete,
    PendingUpdate,
    PendingReroute,
    PendingIsolate
}
