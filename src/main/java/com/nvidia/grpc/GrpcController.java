package com.nvidia.grpc;

import com.nvidia.controller.NodeEntityController;
import com.nvidia.nvlink5.NetworkInformationBaseServiceGrpc;
import com.nvidia.nvlink5.NodeList;
import com.nvidia.nvlink5.Partition;
import com.nvidia.nvlink5.ResponseStatusMessage;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@GrpcService
@Controller
public class GrpcController extends NetworkInformationBaseServiceGrpc.NetworkInformationBaseServiceImplBase {

    @Autowired
    private NodeEntityController nodeEntityController;

    public void create(Partition partition, StreamObserver<ResponseStatusMessage> responseStatusMessage) {

    }

    public void delete(Partition request, StreamObserver<ResponseStatusMessage> responseObserver) {

    }

    public void notify(NodeList request, StreamObserver<ResponseStatusMessage> responseObserver) {

    }


}
