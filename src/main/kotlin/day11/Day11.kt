package day11

import java.awt.Point
import java.io.File
import java.lang.StringBuilder
import kotlin.math.abs

fun main() {

    val file = File("src/main/kotlin/day11/input.txt")

    val input = file.bufferedReader().readLines()

    partOne(input)
    partTwo(input)

}


fun partTwo(input: List<String>) {

    val galaxies : MutableList<Point> = mutableListOf()
    val emptyRows : MutableList<Int> = mutableListOf()
    val emptyCols : List<Int> = getEmptyCols(input)

    for (row in input.indices) {

        if (!input[row].contains('#'))
            emptyRows.add(row)

        for (col in input[row].indices)
            if (input[row][col] == '#')
                galaxies.add(Point(col, row))
    }

    var distances = 0L
    var multiplier = 0

    for (i in galaxies.indices)

        for (j in i + 1..<galaxies.size) {

            val start = galaxies[i]
            val target = galaxies[j]
            distances += start.manhattenDistance(target)

            for (col in emptyCols) {
                if ((target.x > start.x && col in start.x..target.x) ||
                    (target.x < start.x && col in target.x..start.x)
                )
                    multiplier++

            }

            for (row in emptyRows) {
                if ((target.y > start.y && row in start.y..target.y) ||
                    (target.y < start.y && row in target.y..start.y)
                )
                    multiplier++
            }

        }

    val factor = 1000000L
    distances += (factor - 1) * multiplier

    println("Part 02 distances $distances")


}

fun getEmptyCols(input: List<String>): List<Int> {

    val emptyCols : MutableList<Int> = mutableListOf()

    for (col in 0 ..< input[0].length)
        if (input.none { line -> line[col] == '#' })
            emptyCols.add(col)

    return emptyCols

}



fun partOne(input : List<String>) {

    val expanded = expand(input)

    val galaxies : MutableList<Point> = mutableListOf()

    for (row in expanded.indices)
        for (col in expanded[row].indices)
            if (expanded[row][col] == '#')
                galaxies.add(Point(col, row))

    var distances = 0

    for (i in galaxies.indices)
        for (j in i+1 ..< galaxies.size)
            distances += galaxies[i].manhattenDistance(galaxies[j])

    println("Part 01 distances $distances")

}

fun Point.manhattenDistance(p2 : Point) : Int {

    return abs(this.x - p2.x) + abs(this.y - p2.y)
}

fun expand(input: List<String>): List<String> {

    var width = input[0].length

    var result : MutableList<String> = mutableListOf()
    result.addAll(input)

    for (col in (0 ..< width).reversed())
        if (result.none { line -> line[col] == '#' })
            result = result.map { s -> StringBuilder(s).insert(col, '.').toString()}.toMutableList()

    width = result[0].length

    for (row in result.indices.reversed()) {
        if (!result[row].contains('#')) {
            val sb = StringBuilder()
            for (i in 0 ..< width)
                sb.append('.')
            result.add(row, sb.toString())
        }

    }

    return result

}


