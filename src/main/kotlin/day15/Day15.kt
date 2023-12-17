package day15

import java.io.File

fun main() {


    val file = File("src/main/kotlin/day15/input.txt")
    val input = file.bufferedReader().readLine().split(",")

    var sum = 0
    for (line in input) {
        sum += hash(line)

    }

    println("Part 01: $sum")

}


fun hash(input : String) : Int {

    var result = 0

    for (c in input) {
        result += c.code
        result *= 17
        result = result.mod(256)
    }

    return result
}