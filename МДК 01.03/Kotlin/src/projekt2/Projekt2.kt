package projekt2

fun phoneNumber(phone: String): String {
   val regex = Regex(".*(\\d{1})(\\d{3})(\\d{3})(\\d{2})(\\d{2})$")

   return phone.replace(regex, "+$1 ($2) $3-$4-$5")
}

fun main() {
   println("Введите телефон:")
   val input = readLine().toString()
   if (input.length == 11) {
      println(phoneNumber(input))
   } else {
      println("неправильно набран номер")
   }
}