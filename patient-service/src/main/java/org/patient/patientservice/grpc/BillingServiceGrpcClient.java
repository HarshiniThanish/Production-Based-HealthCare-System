package org.patient.patientservice.grpc;
import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class BillingServiceGrpcClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;
    //localhost:9001/BillingService/CreatePateintAccount
    //aws.grpc:123123/BillingService/CreatePateintAccount
    public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress,@Value("${billing.service.grpc.port:9001}")int serverPort){

        log.info("Connecting to the billing service with grpc at {}:{}",serverAddress,serverPort);
        ManagedChannel channel= ManagedChannelBuilder.forAddress(serverAddress,serverPort).usePlaintext().build();

        blockingStub=BillingServiceGrpc.newBlockingStub(channel);

    }
    public BillingResponse createBillingAccount(String PatientID,String name,String email){

        BillingRequest request= BillingRequest.newBuilder().setPatientID(PatientID).setName(name).setEmail(email).build();

        BillingResponse response=blockingStub.createBillingAccount(request);
        log.info("Received response from biling service via GRPC: {}",response);

        return response;


    }
}
