import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication

class TestMain : StringSpec({
    "should retrieve root path correctly" {
	    withTestApplication(Application::adder) {
		    handleRequest(HttpMethod.Get, "/").apply {
			    this.response.status() shouldBe HttpStatusCode.OK
			    this.response.content shouldBe "Hello, World"
		    }

	    }
    }
	"should count routes properly" {
		withTestApplication(Application::adder) {
			handleRequest(HttpMethod.Get, "/count/0").apply {
				this.response.status() shouldBe HttpStatusCode.OK
				this.response.content shouldBe "1"
			}

		}
	}
	"should accept post calculator request" {
		withTestApplication(Application::adder) {
			handleRequest(HttpMethod.Post, "/calculate") {
				addHeader("Content-Type", "application/json")
				setBody("{operation: \"add\", \"first\": 7, \"second\": 3}".trimIndent())
			}.apply { println(response.content) }
		}
	}
})
