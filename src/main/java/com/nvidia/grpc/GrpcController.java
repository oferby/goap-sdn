package com.nvidia.grpc;

import com.nvidia.nvlink5.FabricManagerServiceGrpc;
import com.nvidia.nvlink5.NetworkInformationBaseServiceGrpc;
import com.nvidia.nvlink5.Partition;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Controller;

@GrpcService
@Controller
public class GrpcController extends NetworkInformationBaseServiceGrpc.NetworkInformationBaseServiceImplBase {

    public void create(com.nvidia.nvlink5.Partition partition,
                       io.grpc.stub.StreamObserver<com.nvidia.nvlink5.ResponseStatusMessage> responseStatusMessage) {




    }


}
