package projekt1

fun passwordValid(password: String): Boolean {
    val passwordRegex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[_!@#\$%^&*])[A-Za-z0-9_!@#\$%^&*]+\$")
    return password.matches(passwordRegex)
}

fun main() {
    var boll = true
    while (boll) {
        println("Введите пароль:")
        val pass = readLine().toString()

        if (passwordValid(pass)) {
            println("Пароль принят!")
            boll = false
        } else {
            println("Пароль не соответствует требованиям.")
        }
    }
}
