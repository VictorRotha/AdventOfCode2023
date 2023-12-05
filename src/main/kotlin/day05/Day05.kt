package day05

import java.io.File
import kotlin.math.min


fun main() {


        val file = File("src/main/kotlin/day05/input.txt")

        partOne(file)


    }


    fun partOne(file: File) {

        var seeds: List<Long> = listOf()

        val maps: List<MutableList<List<Long>>> = listOf(
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
        )

        var mapIdx: Int = -1
        var minResult = -1L

        file.bufferedReader().forEachLine { line ->

            if (line.isEmpty())
                return@forEachLine

            if (line.startsWith("seeds:")) {
                seeds = line.split(":")[1].trim().split(" ").map { it.toLong() }
                return@forEachLine
            }

            if (line.contains("-"))
                mapIdx++
            else {
                val commands = line.trim().split(" ").map{it.toLong()}
                maps[mapIdx].add(commands)
            }

        }
        for (seed in seeds) {
            var result = seed

            for (map in maps)
                result = mapInput(result, map)

            minResult = if (minResult == -1L)
                result
            else
                min(minResult, result)

        }

        println("Part One: $minResult")

    }

    fun mapInput(input: Long, maps : List<List<Long>>) : Long {

        for (commands in maps) {
            if (input in commands[1]..commands[1] + commands[2]) {
                val diff = input - commands[1]
                return commands[0] + diff

            }
        }

        return input
    }

