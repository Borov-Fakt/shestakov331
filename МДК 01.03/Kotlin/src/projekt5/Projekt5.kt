package projekt5

fun generatePassword(length: Int): String {
    val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+"
    var password = ""

    for (i in 1..length) {
        val randomChar = chars.random()
        password += randomChar
    }
    return password
}

fun main() {
    println("Введите желаемую длину пароля:")
    val length = readLine()?.toIntOrNull() ?: 0

    val password = generatePassword(length)
    println("Ваш новый пароль: $password")
}