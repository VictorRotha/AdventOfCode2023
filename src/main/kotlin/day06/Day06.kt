package day06


import java.io.File

fun main() {


    val file = File("src/main/kotlin/day06/input.txt")

    partOne(file)
    partTwo(file)

}

fun partOne(file: File) {

    val races = handleInputPartOne(file)

    var product = 1

    for (race in races) {
        var wins = 0
        val time = race.first

        for (v in 1..< time) {
            val d = v * (time - v)
            if (d > race.second)
                wins++
        }

        product *= wins



    }

    println("Part 01: $product")

}


fun partTwo(file: File) {

    val race = handleInputPartTwo(file)

        var wins = 0
        val time = race.first

        for (v in 1..<time) {
            val d = v * (time - v)
            if (d > race.second)
                wins++
        }

    println("Part 02: $wins")
}


fun handleInputPartTwo(file: File) : Pair<Long, Long> {

        var times : Long = -1
        var dists : Long = -1

        file.bufferedReader().forEachLine { line ->

            val split = line.split(":")[1].filterNot {it == ' '  }.toLong()
            if (line.startsWith("Time"))
                times = split
            else
                dists = split

        }

        return Pair(times, dists)



    }

fun handleInputPartOne(file: File) : List<Pair<Int, Int>> {

    var times : List<Int> = listOf()
    var dists : List<Int> = listOf()

    file.bufferedReader().forEachLine { line ->

        val split = line.split(":")[1].trim().split(Regex(" +")).map{it.toInt()}

        if (line.startsWith("Time"))
            times = split
        else
            dists = split

    }

    val races : MutableList<Pair<Int, Int>> = mutableListOf()

    for (i  in times.indices)
        races.add(Pair(times[i], dists[i]))

    return races

}
