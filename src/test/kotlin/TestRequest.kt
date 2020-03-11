import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import webAPI.CalculatorRequest

class TestRequest : StringSpec({
    "should add properly" {
        CalculatorRequest("add", 4, 5).compute() shouldBe 9
    }
    "should multiply properly" {
        CalculatorRequest("multiply", 4, 5).compute() shouldBe 20
    }
})
