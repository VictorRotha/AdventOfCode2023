package day02

import java.io.File



fun main() {

    val file = File("src/main/kotlin/day02/input.txt")

    solution(file)


}

fun solution(file: File) {

    val maxValues = mapOf(Pair("red", 12), Pair("green", 13),Pair("blue", 14))


    var sumPartOne = 0
    var sumPartTwo = 0
    file.bufferedReader().forEachLine { line ->

        val minValues = mutableMapOf(Pair("red", 0), Pair("green", 0),Pair("blue", 0))

        var possible = true

        val s = line.split(":")
        val id = s[0].split("Game ")[1].toInt()

        val hands = s[1].split(";", ",").map { it.trim() }

        for (hand in hands) {
            val n = hand.split(" ")[0].toInt()
            val color = hand.split(" ")[1]

            if (n > minValues[color]!!)
                minValues[color] = n

            if (color in maxValues && n > maxValues[color]!!) {
                possible = false
            }
        }

        val power = minValues["red"]!! * minValues["green"]!! * minValues["blue"]!!
        sumPartTwo += power

        if (possible) {
            sumPartOne += id
        }

    }

    println("Part 01: sum is $sumPartOne")
    println("Part 02: sum is $sumPartTwo")


}

