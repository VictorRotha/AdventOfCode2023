package day14

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day14/input.txt")

    val input = file.bufferedReader().readLines().map{it.toCharArray()}

    val blocks = MutableList(input[0].size) {-1}
    var total = 0

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

                total += input.size - newRow

            }
        }


    }

    println("Part 01: $total")


}