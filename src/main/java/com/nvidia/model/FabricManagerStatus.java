package com.nvidia.model;

public enum FabricManagerStatus {
    UnConfigured,
    PartitionConfigOk,
    PendingPartitionConfig,
    PendingHalt,
    HaltFailed,
    PendingResume,
    ResumeFailed
}
