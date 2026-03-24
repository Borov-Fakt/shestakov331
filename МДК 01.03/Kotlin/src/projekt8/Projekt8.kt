package projekt8

fun isPrime(number: Int): Boolean {
    if (number < 2) return false
    for (i in 2..number / 2) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    println("Введите число для проверки:")
    val input = readLine()?.toIntOrNull() ?: 0

    if (isPrime(input)) {
        println("Число $input — простое!")
    } else {
        println("Число $input — не является простым.")
    }
}
