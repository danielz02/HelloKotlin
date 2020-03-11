import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

data class OperationResult(val operation: String, val first: Int, val second: Int, val result: Int)
data class CalculatorRequest(val operation: String, val first: Int, val second: Int) {
    fun compute(): OperationResult {
        val mathResult = when(operation) {
            "add" -> first + second
            "subtract" -> first - second
            "multiply" -> first * second
            "divide" -> first / second
            else -> throw Exception("$operation is not supported!")
        }
        return OperationResult(operation, first, second, mathResult)
    }
}

fun Application.adder() {
    val count = mutableMapOf<Int, Int>()
    install(ContentNegotiation) {
        gson { }
    }
    routing {
        get("/") {
            call.respondText(hello())
        }
        get("/{operation}/{first}/{second}") {
            try {
                val operation = call.parameters["operation"]!!
                val first = call.parameters["first"]!!.toInt()
                val second = call.parameters["second"]!!.toInt()
                val calculatorRequest = CalculatorRequest(operation, first, second)
                call.respond(calculatorRequest.compute())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, e)
            }
        }
        get("/count/{first}") {
            val first = call.parameters["first"]!!.toInt()
            val firstCount = count.getOrDefault(first, 0) + 1
            count[first] = firstCount
            call.respondText(firstCount.toString())
        }
        post("/calculate") {
            try {
                val request = call.receive<CalculatorRequest>()
                call.respond(request.compute())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest)
            }

        }
    }
}
fun main() {
    embeddedServer(Netty, port = 8080, module = Application::adder).start(wait = true)
}

fun hello(): String {
    return "Hello, World"
}
