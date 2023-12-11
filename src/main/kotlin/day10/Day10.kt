package day10

import java.awt.Point
import java.io.File

lateinit var start : Point

fun main() {

    val file  = File("src/main/kotlin/day10/input.txt")

    val input = file.bufferedReader().readLines()

    for (row in input.indices)
        for (col in input[row].indices)
            if (input[row][col] == 'S') {
                start = Point(col, row)
            }

    val startNbs = getStartNeighbours(start, input)

    var previous = start
    var current = startNbs[0]
    var steps = 1

    while(current != start) {

        val next = getNextNode(current, previous, input)
        previous = current
        current = next
        steps++

    }

    print ("Part 01: total steps $steps, farthest ${steps/2}")



}

fun getNextNode(current: Point, previous: Point, input: List<String>): Point {

    //symbol : (x, y, x1, y1)

    val symbols: Map<Char, List<Int>> = mapOf(
        Pair('|', listOf(0, -1, 0, 1)),
        Pair('-', listOf(-1, 0, 1, 0)),
        Pair('L', listOf(0, -1, 1, 0)),
        Pair('J', listOf(-1, 0, 0, -1)),
        Pair('7', listOf(-1, 0, 0, 1)),
        Pair('F', listOf(0, 1, 1, 0)),

        )

    val currentSymbol = input[current.y][current.x]
    val nb1 = Point(symbols[currentSymbol]!![0] + current.x, symbols[currentSymbol]!![1] + current.y)

    if (nb1 != previous)
        return nb1

    return Point(symbols[currentSymbol]!![2] + current.x, symbols[currentSymbol]!![3] + current.y)

}

fun getStartNeighbours(start: Point, input: List<String>): List<Point> {

    //top, right, bottom, left

    val symbols : List<String> = listOf("|F7", "-/J", "|JL", "-LF")
    val nbPositions : List<Point> = listOf(
        Point(0, -1),
        Point(1, 0),
        Point(0, 1),
        Point(-1, 0)
    )

    val nbs = mutableListOf<Point>()
    for (i in 0..< 4) {
        val x = start.x + nbPositions[i].x
        val y = start.y + nbPositions[i].y

        if (x < 0 || y < 0 || y >= input.size || x >= input[start.y].length)
            continue

        val symbol = input[y][x]
        if (symbol in symbols[i]) {
            nbs.add(Point(x, y))
            if (nbs.size == 2)
                break
        }


    }

    return nbs

}
