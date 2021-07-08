package br.com.zup.edu

import io.grpc.ManagedChannelBuilder

fun main(){

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Cassao")
        .setCpf("123.123.456-22")
        .setIdade(27)
        .setSalario(22000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua Tapuios")
            .setCep("38408416")
            .setComplemento("numero 1110").build())
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)

}