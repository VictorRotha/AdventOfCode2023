package day05


import java.io.File
import kotlin.math.min


fun main() {


    val file = File("src/main/kotlin/day05/input.txt")

    partOne(file)
    partTwo(file)


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

fun mapInputReversed(target: Long, maps : List<List<Long>>) : Long {

for (commands in maps) {
    if (target in commands[0]..< commands[0] + commands[2]) {
        val diff = target - commands[0]
        return commands[1] + diff

    }
}

return target
}


fun partTwo(file: File) {

    var seedPairs: MutableList<Pair<Long, Long>> = mutableListOf()

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


    file.bufferedReader().forEachLine { line ->

        if (line.isEmpty())
            return@forEachLine

        if (line.startsWith("seeds:")) {

            val seeds = line.split(":")[1].trim().split(" ").map { it.toLong() }
            var startSeed : Long = -1L
            for (i  in seeds.indices) {
                if (i.mod(2) == 0) {
                    startSeed = seeds[i]
                } else {
                    seedPairs.add(Pair(startSeed, seeds[i]))
                }

            }

            return@forEachLine
        }


        if (line.contains("-"))
            mapIdx++
        else {
            val commands = line.trim().split(" ").map{it.toLong()}
            maps[mapIdx].add(commands)
        }

    }

    processReversed(seedPairs, maps)

    }


fun processReversed(seeds : MutableList<Pair<Long, Long>>, maps : List<MutableList<List<Long>>>) {

    var result = 0L

    var location = 0L

    val maxTries = Long.MAX_VALUE

    while (location < maxTries) {

        result = location

        for (map in maps.reversed())
            result = mapInputReversed(result, map)

        if (isInSeeds(result, seeds)) {
            print("Part Two: $location")
            break
        }

        location++

    }

}

fun isInSeeds(target : Long, seeds : List<Pair<Long, Long>>) : Boolean {

    for (seed in seeds) {

        if (target in seed.first..<seed.first + seed.second)
            return true
    }

    return false

}



fun processSeeds(seeds : MutableList<Pair<Long, Long>>, maps : List<MutableList<List<Long>>>) {
var minResult = -1L

    for (seed in seeds) {

        for (i in seed.first..<seed.first + seed.second) {


            var result = i

            for (map in maps)
                result = mapInput(result, map)

            minResult = if (minResult == -1L) {
                println("first value $result")
                result
            } else {

                val lowest = min(minResult, result)
                if (lowest != minResult)
                    println("lowest: $lowest")
                lowest

            }

        }


    }

    println("Part One: $minResult")

}




