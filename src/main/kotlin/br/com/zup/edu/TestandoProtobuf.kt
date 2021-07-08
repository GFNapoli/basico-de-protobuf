package br.com.zup.edu

import java.io.FileInputStream
import java.io.FileOutputStream

fun main(){

    val request = FuncionarioRequest.newBuilder()
        .setNome("Cassao")
        .setCpf("123.123.456-22")
        .setSalario(22000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua Tapuios")
            .setCep("38408416")
            .setComplemento("numero 1110").build())
        .build()

    println(request)
    request.writeTo(FileOutputStream("funcionariorequest.bin"))

    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionariorequest.bin"))

    request2.setCargo(Cargo.GERENTE)
    println(request2)

}