import dev.alefiengo.grpc.ListUsersRequest
import dev.alefiengo.grpc.UserServiceGrpc
import io.grpc.ManagedChannelBuilder

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val stub = UserServiceGrpc.newBlockingStub(channel)

    val request = ListUsersRequest.newBuilder().setFilter("all").build()

    val userResponses = stub.listUsers(request)

    for (response in userResponses) {
        println("User: user_id=${response.userId}, name=${response.name}, email=${response.email}")
    }

    channel.shutdown()
}
