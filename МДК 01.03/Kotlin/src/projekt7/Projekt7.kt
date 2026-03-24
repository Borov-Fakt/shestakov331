package projekt7

fun reverseString(text: String): String {
    return text.reversed()
}

fun main() {
    println("Введите текст:")
    val input = readLine() ?: ""

    val result = reverseString(input)
    println("Перевернутая строка: $result")
}
