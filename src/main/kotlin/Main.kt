import kotlin.random.Random

fun main() {
    val gNumber = generateNumber() //read-only guessing number
    var iNumber: String? = null //your input
    var numAttempts = 0 //calculate the attempts for the right guessing

    println("WELCOME TO MASTERMIND")
    //println(gNumber)

    // as long we have not guessed the right number repeat
    while (iNumber != gNumber) {
        print("Guess a 4 digit number without repeating digits: ")
        iNumber = readLine() //read-in your number
        if (!isValidNumber(iNumber)) { //check if the input is correct -> 4 digits and no repeating digits
            println("ERROR!")
            println("Please input a 4 digit number without repeating digits")
            continue
        }
        numAttempts++
        val (n, m) = compareNumbers(gNumber, iNumber!!) //get back the status of your input <-> gNumber (right digit | right position)
        println("$n:$m")
    }

    println("IN $numAttempts ATTEMPTS YOU GUESSED THE NUMBER $gNumber")
}

//check if input number is valid
fun isValidNumber(number: String?): Boolean {
    if (number == null || number.length != 4) {
        return false
    }

    val digits = mutableSetOf<Char>() //empty set of chars
    for (digit in number) {
        //if wrong char or already a digit in digits is then it's not a valid number
        if (digit !in '0'..'9' || digit in digits) {
            return false
        }
        digits.add(digit)
    }
    return true
}

// generate the gNumber
fun generateNumber(): String {
    var gNumber = "";
    val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    //4 digit number
    repeat(4)
    {
        val index = Random.nextInt(digits.size) //get random index
        gNumber += digits[index] //add to String the number from index
        digits.removeAt(index) //remove it to have no repeating values
    }

    return gNumber
}

//compare both numbers and give back n,m
fun compareNumbers(gNumber: String, iNumber: String): Pair<Int, Int> {
    var (n, m) = 0 to 0
    val gDigits = mutableSetOf<Char>()

    //check m (right position)
    for (i in 0..3) {
        if (iNumber[i] == gNumber[i]) {
            m++
        }
        gDigits.add(gNumber[i])
    }

    //check n (right digit)
    for (i in 0..3) {
        if (iNumber[i] in gDigits) {
            n++
        }
    }
    //return Pair(n-m , m)
    return Pair(n , m)
}
