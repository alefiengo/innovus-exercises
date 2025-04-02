import dev.alefiengo.grpc.GetUserRequest
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
