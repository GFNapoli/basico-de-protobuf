package br.com.zup.edu

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun main(){

    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndpoint())
        .build()

    server.start()
    server.awaitTermination()
}

class FuncionarioEndpoint: FuncionarioServiceGrpc.FuncionarioServiceImplBase(){

    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioReply>?) {

        println(request)

        var nome: String? = request?.nome
        if (!request!!.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))){
            nome = "???"
        }

        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val data = Timestamp.newBuilder()
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()

        val response = FuncionarioReply.newBuilder()
            .setNome(nome)
            .setData(data)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}