package day02

import java.io.File



fun main() {

    val file = File("src/main/kotlin/day02/input.txt")

    partOne(file)



}

fun partOne(file: File) {

    val maxValues = mapOf(Pair("red", 12), Pair("green", 13),Pair("blue", 14))

    var sum = 0
    file.bufferedReader().forEachLine { line ->

        var possible = true

        val s = line.split(":")
        val id = s[0].split("Game ")[1].toInt()

        val hands = s[1].split(";", ",").map { it.trim() }

        for (hand in hands) {
            val n = hand.split(" ")[0].toInt()
            val color = hand.split(" ")[1]

            if (color in maxValues && n > maxValues[color]!!) {
                possible = false
                break
            }
        }

        if (possible) {
            sum += id
        }

    }

    println("Part 01: sum is $sum")


}
