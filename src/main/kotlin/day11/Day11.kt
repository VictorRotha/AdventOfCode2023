package day11

import java.awt.Point
import java.io.File
import java.lang.StringBuilder
import kotlin.math.abs

fun main() {

    val file = File("src/main/kotlin/day11/input.txt")

    val input = file.bufferedReader().readLines()

    val expanded = expand(input)


    val galaxies : MutableList<Point> = mutableListOf()

    for (row in expanded.indices)
        for (col in expanded[row].indices)
            if (expanded[row][col] == '#')
                galaxies.add(Point(col, row))

    var paths = 0
    var distances = 0

    for (i in galaxies.indices)
        for (j in i+1 ..< galaxies.size) {
            paths++
            distances += galaxies[i].manhattenDistance(galaxies[j])

        }

    println("number of paths $paths")
    println("distances $distances")


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


