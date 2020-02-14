fun main() {
    println("Hello, World!")
    println(add(1, 2))
}

fun add(first: Int, second: Int): Int {
    require(first >= 0) {"first should be positive!"}
    return first + second
}

fun hello(): String {
    return "Hello, World!"
}