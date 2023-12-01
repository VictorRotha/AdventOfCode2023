package day01

import java.io.File

    val digits = listOf("0","1","2","3","4","5","6","7","8","9")
    val spelled = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun main() {

        val file = File("src/main/kotlin/day01/input.txt")

        partOne(file)
        partTwo(file)


    }

    fun partOne(file : File) {

        val numbers = mutableListOf<Int>()
        val br = file.bufferedReader()


        br.forEachLine {line ->

            var firstDigit = ""
            var lastDigit = ""

            for (c in line) {
                if (c.toString() in digits) {
                    firstDigit = c.toString()
                    break
                }
            }

            for (c in line.reversed())
                if (c.toString() in digits) {
                    lastDigit = c.toString()
                    break
                }

            numbers.add((firstDigit + lastDigit).toInt())

    }

    println("Part One: ${numbers.sum()}")


}

fun partTwo(file : File) {

    val numbers = mutableListOf<Int>()

    val br = file.bufferedReader()

    br.forEachLine { line ->

        val firstDigit = lookForDigit(line, false)
        val lastDigit = lookForDigit(line, true)

        val number = firstDigit * 10 + lastDigit

        numbers.add(number)

    }

    println("Part Two: ${numbers.sum()}")


}

fun lookForDigit(line : String, reversed: Boolean) : Int {

    val range  = if (reversed) line.indices.reversed() else line.indices

    for (i in range) {
        val c = line[i]

        if (c.toString() in digits) {
            return c.toString().toInt()
        } else {
            var n : Int = checkSequence(i, line, 3)
            if (n > -1) {
                return n
            }
            n = checkSequence(i, line, 4)
            if (n > -1) {
                return n
            }
            n = checkSequence(i, line, 5)
            if (n > -1) {
                return n
            }

        }

    }

    return -1

}

fun checkSequence(i : Int, line : String, l : Int) : Int {

    if (i <= line.length - l) {
        val sub = line.subSequence(i, i + l)
        if (sub in spelled) {
            return spelled.indexOf(sub) + 1
        }
    }

    return -1
}




