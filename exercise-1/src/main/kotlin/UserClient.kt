import dev.alefiengo.grpc.GetUserRequest
import dev.alefiengo.grpc.UserServiceGrpc
import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val stub = UserServiceGrpc.newBlockingStub(channel)

    val request = GetUserRequest.newBuilder().setUserId("10").build()

    val response = stub.getUser(request)
    println("Response: user_id=${response.userId}, name=${response.name}, email=${response.email}")

    channel.shutdown()
}
