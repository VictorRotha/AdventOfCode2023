package day14

import java.io.File
import java.lang.StringBuilder

fun main() {

    val file = File("src/main/kotlin/day14/input.txt")

    val input = file.bufferedReader().readLines().map{it.toCharArray()}

    part01(input)
    part02(input)


}

fun part01(input : List<CharArray>) {

    tiltNorth(input)
    println("Part 01: ${weight(input)}")

}

fun part02(input: List<CharArray>) {

    var map = input

    var cycles = 1_000_000_000
    val cache : MutableMap<String, Int> = mutableMapOf(
        Pair(makeKey(map), 0)
    )

    var first = -1
    var second = -1

    for (i in 1 .. cycles) {
        map = fullCycle(map)

        val key = makeKey(map)
        if (key in cache) {
            first = cache[key]!!
            second = i
            break
        }
        cache[key] = i
    }

    cycles = (cycles - first).mod(second - first)

    for (i in 0 ..< cycles)
        map = fullCycle(map)

    println("Part 02: ${weight(map)}")


}



fun fullCycle(input: List<CharArray>) : List<CharArray> {

    var map = input

    for (i in 0 ..< 4) {
        tiltNorth(map)
        map = rotateRight(map)
    }

    return map

}

fun rotateRight(input: List<CharArray>) : List<CharArray> {

    val result : MutableList<CharArray> = mutableListOf()

    for (i in input[0].indices) {
        result.add(CharArray(input.size))
    }

    for (row in input.indices) {
        for (col in input[row].indices) {
            result[col][result.size - 1 - row] = input[row][col]
        }
    }

    return result

}


fun tiltNorth(input: List<CharArray>) {

    val blocks = MutableList(input[0].size) {-1}

    for (row  in input.indices) {

        val line = input[row]

        for (col in line.indices) {

            val c = line[col]

            if (c == '#')

                blocks[col] = row

            else if (c == 'O') {

                val newRow = blocks[col] + 1
                blocks[col] = newRow

                if (row != newRow) {
                    input[newRow][col] = 'O'
                    input[row][col] = '.'
                }

            }
        }

    }

}

fun makeKey(input: List<CharArray>) : String {

    val sb = StringBuilder()
    for (line in input)
        sb.append(line)

    return sb.toString()

}
fun weight(input: List<CharArray>) : Int {

    var total = 0

    for (row in input.indices)
        for (col in input[0].indices) {

            if (input[row][col] == 'O')
               total += input.size - row

        }

    return total

}
