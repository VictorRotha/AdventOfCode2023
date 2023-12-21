package day16

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day16/input.txt")

    val input = file.bufferedReader().readLines()

    var heads : MutableSet<Node> = mutableSetOf()
    heads.add(Node(0, 0, -1, 0))
    val path : MutableSet<Node> = mutableSetOf()
    path.addAll(heads)

    while (heads.size > 0) {

        val newHeads = mutableSetOf<Node>()
        for (head in heads) {

            val s = input[head.y][head.x]

            var dx = head.x - head.prevX
            var dy = head.y - head.prevY

            var dx2 = 0
            var dy2 = 0

            var split = false

            when (s) {

                '|' -> {
                    if (dx != 0) {

                        dx = 0
                        dy = -1

                        split = true

                        dx2 = 0
                        dy2 = 1

                    }
                }
                '-' -> {
                    if (dy != 0) {

                        dx = -1
                        dy = 0

                        split = true

                        dx2 = 1
                        dy2 = 0

                    }

                }
                '/' -> {
                    if (dx != 0) {
                        dy = -dx
                        dx = 0
                    } else {
                        dx = -dy
                        dy = 0
                    }

                }
                '\\' -> {
                    if (dx != 0) {
                        dy = dx
                        dx = 0
                    } else {
                        dx = dy
                        dy = 0
                    }

                }

            }

            path.add(head)

            val next = Node(head.x + dx, head.y + dy, head.x, head.y)
            if (isValidHead(next, path, input))
                newHeads.add(next)

            if (split) {
                val next2 = Node(head.x + dx2, head.y + dy2, head.x, head.y)
                if (isValidHead(next2, path, input))
                    newHeads.add(next2)
            }

        }

        heads = newHeads

    }

    println("Part 01: ${countPath(path)}")


}

fun isValidHead(next: Node, path: Set<Node>, grid: List<String>) : Boolean {

    return isInGrid(next, grid) && !isInPath(next, path)
}

fun isInPath(next: Node, path: Set<Node>) : Boolean {

    return path.contains(next)
}

fun isInGrid(next: Node, grid: List<String>): Boolean {
    return next.x in 0..< grid[0].length && next.y in grid.indices
}

fun countPath(path: Set<Node>) : Int {
    val result = mutableSetOf<Node>()
    for (node in path) {
        result.add(Node(node.x, node.y, 0, 0))
    }

    return result.size

}

fun printPath(grid: List<String>, path: Set<Node>) {

    val result : MutableList<CharArray> = mutableListOf()

    for (line in grid)
        result.add(line.toCharArray())

    for (node in path) {
        result[node.y][node.x] = '#'
    }

    for (line in result) {
        for (c in line) {
            print(c)
        }
        println()
    }


}

data class Node (
    var x : Int,
    var y : Int,
    var prevX: Int,
    var prevY: Int
)