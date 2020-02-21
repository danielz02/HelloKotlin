import io.kotlintest.specs.StringSpec
import io.ktor.server.testing.withTestApplication

class TestMain : StringSpec({
    "should retrieve root path correctly" {
	    withTestApplication {  }
    }
})
