package day03

import java.io.File
import java.lang.NumberFormatException


lateinit var input : List<String>
val noSymbol = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")

val stars = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

fun main() {

    //537732
    //84883664

    val file = File("src/main/kotlin/day03/input.txt")
//    part01(file)
    part02(file)





}

fun part01(file: File) {

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
                    hasSymbol = checkNeighboursForSymbols(col, row)


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

fun checkNeighboursForSymbols(x : Int, y : Int) : Boolean {

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


fun part02(file: File) {

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

                if (numberString.isNotEmpty()) {
                    val number = numberString.toInt()
                    checkNbsForStars(nbs, number)
                    numberString = ""
                    nbs.clear()
                }

            }
        }

        if (numberString.isNotEmpty()) {
            val number = numberString.toInt()
            checkNbsForStars(nbs, number)
        }


    }

    val result = stars
        .filter { it.value.size == 2}
        .map { it.value[0] * it.value[1] }
        .sum()

    println("Part 02:  $result")


}

fun checkNbsForStars(nbs : Set<Pair<Int, Int>>, number : Int) {

    for (nb in nbs) {

        val s = input[nb.second][nb.first].toString()
        if (s == "*") {
            stars.putIfAbsent(nb, mutableListOf())
            stars[nb]!!.add(number)
        }

    }


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

