package projekt4

fun sumOfDigits(number: Int): Int {
    var n = number
    var sum = 0
    while (n > 0) {
        sum += n % 10
        n /= 10
    }
    return sum
}
fun main() {
    println("Введите целое число:")
    val input = readLine()?.toIntOrNull() ?: 0
    val result = sumOfDigits(input)
    println("Сумма цифр числа $input равна: $result")
}