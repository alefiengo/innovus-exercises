package dev.alefiengo

import dev.alefiengo.grpc.GetUserRequest
import dev.alefiengo.grpc.ListUsersRequest
import dev.alefiengo.grpc.UserResponse
import dev.alefiengo.grpc.UserServiceGrpc
import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

class UserServiceImpl : UserServiceGrpc.UserServiceImplBase() {
    override fun getUser(request: GetUserRequest, responseObserver: StreamObserver<UserResponse>) {
        val response = UserResponse.newBuilder()
            .setUserId(request.userId)
            .setName("Alejandro")
            .setEmail("alejandro@mail.com")
            .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun listUsers(
        request: ListUsersRequest,
        responseObserver: StreamObserver<UserResponse>
    ) {
        val users = listOf(
            UserResponse.newBuilder().setUserId("1").setName("Alejandro").setEmail("alejandro@mail.com")
                .build(),
            UserResponse.newBuilder().setUserId("2").setName("Jose").setEmail("jose@mail.com")
                .build(),
            UserResponse.newBuilder().setUserId("3").setName("Cintia")
                .setEmail("cintia@mail.com").build()
        )

        for (user in users) {
            responseObserver.onNext(user)
        }

        responseObserver.onCompleted()
    }
}

fun main() {
    val port = 50051
    val server: Server = ServerBuilder.forPort(port)
        .addService(UserServiceImpl())
        .build()
        .start()

    println("Server started on: localhost:$port")
    server.awaitTermination()
}
