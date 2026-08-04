package org.patient.billingservice.grpc;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGRPCService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log= LoggerFactory.getLogger(BillingGRPCService.class);
    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver){
        log.info("createBillingAccount Required {}",billingRequest.toString());
        //Business Logic - save to db,perform calculations

        BillingResponse response=BillingResponse.newBuilder().setAccountID("1234").setStatus("Active").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
