package day04


import java.io.File
import kotlin.math.pow

fun main() {

    val file = File("src/main/kotlin/day04/input.txt")

    solutions(file)

}

fun solutions(file: File) {

    var partOneSum = 0
    val cards : MutableMap<Int, Int> = mutableMapOf()

    file.bufferedReader().forEachLine { line ->

        val id = line.split(":")[0].split(Regex(" +"))[1].toInt()
        val numbers = line.split(":")[1].split("|").map{ it.trim() }
        val target = numbers[0].split(Regex(" +"))
        val yourNumbers = numbers[1].split(Regex(" +"))

        cards.putIfAbsent(id, 0)
        cards[id] = cards[id]!! + 1

        val n = target.intersect(yourNumbers.toSet()).size
        if (n > 0) {
            val points = 2.0.pow((n - 1).toDouble()).toInt()
            partOneSum += points

            for (i in id + 1 .. id + n) {
                cards.putIfAbsent(i, 0)
                cards[i] = cards[i]!! + cards[id]!!
            }

        }

    }

    println("Part One: $partOneSum ")

    val partTwoSum = cards.map{ it.value }.sum()
    println("Part Two: $partTwoSum")


}

