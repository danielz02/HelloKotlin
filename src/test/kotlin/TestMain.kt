import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class TestMain : StringSpec({
    "test the add method" {
        assert(add(5, 6) == 11)
        require(add(0, 1) == 1)
        add(5, 6) shouldBe 11
    }
})