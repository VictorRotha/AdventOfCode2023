package day08

import java.io.File

var instructions : String = ""
val nodes : MutableMap<String, List<String>> = mutableMapOf()
val startNodes : MutableList<String> = mutableListOf()

fun main() {

    readInput()
    partOne()

}

fun partOne() {
    var currentNode = "AAA"
    var steps = 0
    var targetReached  = false
    while (!targetReached) {

        for (direction in instructions) {
            steps++
            currentNode =
                if (direction == 'L') {
                    nodes[currentNode]!![0]
                } else
                    nodes[currentNode]!![1]

            if (currentNode == "ZZZ") {
                println("Found ZZZ after $steps steps")
                targetReached = true
                break
            }

        }
    }
}


fun readInput() {

    val file = File("src/main/kotlin/day08/input.txt")

    file.bufferedReader().forEachLine { line ->
        if (line.isEmpty())
            return@forEachLine

        if (!line.contains("="))
            instructions = line
        else {

            val name = line.split("=")[0].trim()
            val nbs = line.split("=")[1].filter { it != '(' && it != ')' }.trim().split(", ")

            if (name.endsWith("A"))
                startNodes.add(name)

            nodes[name] = nbs

        }

    }

}




