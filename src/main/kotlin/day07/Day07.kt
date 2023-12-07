package day07

import java.io.File

fun main() {

    val input = readInput()

    println("Part01 ${solution(input, ::compare)}")
    println("Part02 ${solution(input, ::compareTwo)}")

}

fun readInput() : Map<String, Int> {

    val file = File("src/main/kotlin/day07/input.txt")

    val input : MutableMap<String, Int> = mutableMapOf()

    file.bufferedReader().forEachLine { line ->
        val pair = line.split(" ")
        input[pair[0]] = pair[1].toInt()
    }

    return input

}

fun solution(input: Map<String, Int>, compare : (s1 : String, s2 : String) -> Int) : Int {

    val c : Comparator<String> = Comparator { s1: String, s2: String ->
        return@Comparator compare(s1, s2)
    }

    val sorted = input.keys.toList().sortedWith(c)
    var sum = 0
    for (i in sorted.indices)
        sum += input[sorted[i]]!! * (i + 1)

    return sum
}


fun compareTwo(s1 :String, s2:String) : Int {

    if (s1 == s2)
        return 0

    val map1 = createMap(s1)
    val map2 = createMap(s2)

    val noJ1 = map1['J'] ?: 0
    val noJ2 = map2['J'] ?: 0

    val map1J = map1.filter {
        it.key != 'J'
    }
    val map2J = map2.filter {
        it.key != 'J'
    }


    val amounts1 = map1J.values.toList().sortedDescending().toMutableList()
    val amounts2 = map2J.values.toList().sortedDescending().toMutableList()

    if (amounts1.isEmpty())
        amounts1.add(5)
    else
        amounts1[0] += noJ1

    if (amounts2.isEmpty())
        amounts2.add(5)
    else
        amounts2[0] += noJ2


    if (amounts1[0] != amounts2[0])
        return amounts1[0] - amounts2[0]

    if (amounts1[0] < 5 && amounts1[1] != amounts2[1]) {
        return amounts1[1] - amounts2[1]
    }

    val ranking = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A' )

    for (i in s1.indices) {
        val c1 = s1[i]
        val c2 = s2[i]

        if (c1 == c2)
            continue

        return ranking.indexOf(c1) - ranking.indexOf(c2)

    }

    return 0

}

fun compare(s1 :String, s2:String) : Int {
    if (s1 == s2)
        return 0

    val map1 = createMap(s1)
    val map2 = createMap(s2)

    val amounts1 = map1.values.toList().sortedDescending()
    val amounts2 = map2.values.toList().sortedDescending()


    if (amounts1[0] != amounts2[0])
        return amounts1[0] - amounts2[0]

    if (amounts1[0] < 5 && amounts1[1] != amounts2[1])
        return amounts1[1] - amounts2[1]

    val ranking = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' )

    for (i in s1.indices) {
        val c1 = s1[i]
        val c2 = s2[i]

        if (c1 == c2)
            continue

        return ranking.indexOf(c1) - ranking.indexOf(c2)

    }

    return 0

}


fun createMap(s: String) : Map<Char, Int> {

    val result : MutableMap<Char, Int> = mutableMapOf()
    for (c in s) {
        result.putIfAbsent(c, 0)
        result[c] = result[c]!! + 1

    }

    return result

}