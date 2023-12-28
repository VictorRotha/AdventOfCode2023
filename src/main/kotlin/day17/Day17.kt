package day17

import java.awt.Point
import java.io.File
import java.util.PriorityQueue


import kotlin.time.measureTime

fun main() {

    val file = File("src/main/kotlin/day17/input.txt")

    val input = readInput(file)
    var result : Int
    var dur = measureTime {
        result = findMinLoss(input, 0, 3)
    }
    println("Part One: $result ($dur)")


    dur = measureTime {
        result = findMinLoss(input, 4, 10)
    }
    println("Part Two: $result ($dur)")

}

fun findMinLoss(input: List<String>, min : Int, max: Int) : Int{

    val queue  = PriorityQueue<Node>()
    val visited = mutableSetOf<Node>()

    val costs : MutableMap<Node, Int> = mutableMapOf()

    val target = Point(input.last().lastIndex, input.lastIndex)

    queue.add(Node(Point(0,0),0, 0))

    while (queue.isNotEmpty()) {

        val current = queue.remove()

        if (current.pos == target)
            return costs.getOrDefault(current, -1)

        visited.add(current)

        val nbs = findNeighbours(current, input, visited, min, max)

        for (nb in nbs) {
            val oldCost = costs.getOrDefault(nb, Integer.MAX_VALUE)
            if (nb.cost < oldCost) {
                costs[nb] = nb.cost
                queue.add(nb)
            }

        }

    }

return -1

}


fun findNeighbours(node: Node, input: List<String>, visited: Set<Node>, min: Int, max: Int) : List<Node> {

    val result = mutableListOf<Node>()

    val nbx = listOf(0, 1, 0, -1)
    val nby = listOf(-1, 0, 1, 0)

    for (i in 0 ..< 4) {

        if (node.dx != 0 && nbx[i] != 0)
            continue

        if (node.dy != 0 && nby[i] != 0)
            continue

        var incr = 0
        for (dist in 1.. max) {

            val x = node.pos.x + nbx[i] * dist
            val y = node.pos.y + nby[i] * dist

            if (x !in input[0].indices || y !in input.indices)
                continue

            incr += input[y][x].digitToInt()

            if (dist < min)
                continue

            val nb = Node(Point(x, y), x - node.pos.x, y - node.pos.y)
            nb.cost = node.cost + incr

            if (nb in visited)
                continue

            result.add(nb)

        }
    }

    return result
}

fun readInput(file: File) : List<String>{
    return file.bufferedReader().readLines()

}


data class Node (
    val pos : Point,
    val dx: Int,
    val dy: Int
) : Comparable<Node> {

    var cost : Int = 0

    override fun compareTo(other: Node): Int = this.cost.compareTo(other.cost)

}
