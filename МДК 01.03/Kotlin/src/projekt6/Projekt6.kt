package projekt6

fun findChars(text: String): List<Char> {
    val seen = mutableListOf<Char>()
    val duplicates = mutableListOf<Char>()

    for (char in text) {
        if (seen.contains(char)) {
            if (!duplicates.contains(char)) {
                duplicates.add(char)
            }
        } else {
            seen.add(char)
        }
    }
    return duplicates
}

fun main() {
    println("Введите строку символов:")
    val input = readLine().toString()

    val result = findChars(input)

    if (result.size == 0) {
        println("Повторов нет.")
    } else {
        println("Дубликаты: $result")
    }
}