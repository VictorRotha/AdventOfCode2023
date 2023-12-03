package day03

import java.io.File
import java.lang.NumberFormatException


lateinit var input : List<String>
val noSymbol = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")
val stars = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()
var partOneSum = 0

fun main() {

    val file = File("src/main/kotlin/day03/testinput.txt")

    solutions(file)

}

fun solutions(file: File) {

    input = file.bufferedReader().readText().split("\n")

    for (row in input.indices) {

        val line = input[row]

        var numberString  = ""
        val nbs : MutableSet<Pair<Int, Int>> = mutableSetOf()

        for (col in line.indices) {

            val s = line[col].toString()

            try {
                s.toInt()
                numberString += s
                nbs.addAll(getNeighbours(col, row))

            } catch (_: NumberFormatException) {

                handleNumberString(numberString, nbs)
                numberString = ""
                nbs.clear()

            }
        }

        handleNumberString(numberString, nbs)


    }

    println("Part 01: $partOneSum")

    val partTwoResult = stars
        .filter { it.value.size == 2}
        .map { it.value[0] * it.value[1] }
        .sum()

    println("Part 02: $partTwoResult")

}

fun handleNumberString(s : String, nbs : Set<Pair<Int, Int>>) {

    if (s.isNotEmpty()) {
        val number = s.toInt()

        val star = checkNbsForStars(nbs)
        star?.let {
            stars.putIfAbsent(it, mutableListOf())
            stars[it]!!.add(number)
        }
        if (checkNbsForSymbols(nbs)) {
            partOneSum += number
        }
    }



}

fun checkNbsForStars(nbs : Set<Pair<Int, Int>>) : Pair<Int, Int>? {

    for (nb in nbs) {

        val s = input[nb.second][nb.first].toString()
        if (s == "*")
            return nb

    }

    return null
}

fun checkNbsForSymbols(nbs : Set<Pair<Int, Int>>) : Boolean{

    for (nb in nbs) {

        val s = input[nb.second][nb.first].toString()
        if (s !in noSymbol) {
            return true
        }

    }

    return false

}

fun getNeighbours(x : Int, y : Int) : List<Pair<Int, Int>> {

    val result : MutableList<Pair<Int, Int>> = mutableListOf()

    for (row in y-1 ..y + 1) {
        for (col in x -1 .. x + 1) {
            if (row < 0 || col < 0 || row >= input.size || col >= input[row].length )
                continue
            result.add(Pair(col, row))

        }

    }

    return result

}
