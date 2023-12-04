package day04


import java.io.File
import kotlin.math.pow

fun main() {

    val file = File("src/main/kotlin/day04/input.txt")

    partOne(file)

}

fun partOne(file: File) {

    var sum = 0

    file.bufferedReader().forEachLine { line ->

        val numbers = line.split(":")[1].split("|").map{ it.trim() }

        val target = numbers[0].split(Regex(" +"))
        val yourNumbers = numbers[1].split(Regex(" +"))

        val n = target.intersect(yourNumbers.toSet()).size
        if (n > 0) {
            val points = 2.0.pow((n - 1).toDouble()).toInt()
            sum += points

        }

    }

    println("Part One: $sum ")


}