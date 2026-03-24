package projekt9

fun convert(value: Double, mode: String): Double {
    if (mode == "1") {
        return (value * 9 / 5) + 32
    } else {
        return (value - 32) * 5 / 9
    }
}

fun main() {
    println("Выберите режим: 1 (C в F) или 2 (F в C)")
    val choice = readLine() ?: ""

    if (choice == "1" || choice == "2") {
        println("Введите число для перевода:")
        val input = readLine()?.toDoubleOrNull() ?: 0.0

        val result = convert(input, choice)
        println("Результат: $result")
    } else {
        println("Ошибка: введите 1 или 2")
    }
}