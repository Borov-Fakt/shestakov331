package projekt3

fun getEvenNumbers(numbers: List<Int>): List<Int> {

    return numbers.filter { it % 2 == 0 }
}

fun main() {
    val myNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    print("Четные числа: ")
    print(getEvenNumbers(myNumbers))
}