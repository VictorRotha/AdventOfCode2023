package day03

import java.io.File
import java.lang.NumberFormatException


lateinit var input : List<String>
val noSymbol = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")

fun main() {

    //537732

    val file = File("src/main/kotlin/day03/input.txt")

    input = file.bufferedReader().readText().split("\n")

    var sum = 0

    for (row in input.indices) {

        val line = input[row]

        var numberString  = ""
        var hasSymbol = false

        for (col in line.indices) {

            val s = line[col].toString()

            try {
                s.toInt()
                numberString += s
                if (!hasSymbol)
                    hasSymbol = checkNeighbours(col, row)


            } catch (_: NumberFormatException) {

                if (numberString.isNotEmpty() && hasSymbol) {
                    val number = numberString.toInt()
                    sum += number
                    hasSymbol = false
                }
                numberString = ""
            }
        }

        if (numberString.isNotEmpty() && hasSymbol) {
            val number = numberString.toInt()
            sum += number
        }


    }

    println("Part 01:  $sum")



}

fun checkNeighbours(x : Int, y : Int) : Boolean {

    for (row in y-1 ..y + 1) {
        for (col in x -1 .. x + 1) {
            if (row < 0 || col < 0 || row >= input.size || col >= input[row].length )
                continue
            val s = input[row][col].toString()
            if (s !in noSymbol) {
                return true
            }
        }

    }

    return false
}

