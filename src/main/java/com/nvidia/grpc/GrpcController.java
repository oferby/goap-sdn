package com.nvidia.grpc;

import com.nvidia.nvlink5.FabricManagerServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Controller;

@GrpcService
@Controller
public class GrpcController extends FabricManagerServiceGrpc.FabricManagerServiceImplBase {




}
