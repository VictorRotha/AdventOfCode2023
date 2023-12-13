package day12

import java.io.File

fun main() {

    val file = File("src/main/kotlin/day12/input.txt")

    val input = file.bufferedReader().readLines()

    part01(input)
}


fun part01(input: List<String>) {

    for (line in input) {

        processLine(line)
    }

    println("Part 01: $solSum")

}

fun processLine(line: String) {

    val record = line.split(" ")[0]
    val groups = line.split(" ")[1].split(",").map{it.toInt()}

    findNext(record.toCharArray(), groups)

}

var solSum = 0

fun findNext(record: CharArray, target: List<Int>) : Boolean {

    val groups = createGroups(String(record))

    if (groups == target && !record.contains('?')) {
        solSum++
    }


    var idx : Int = -1
    for (i in record.indices)
        if (record[i] == '?') {
            idx = i
            break
        }

    if (idx == -1)
        return false

    record[idx] = '#'
    if (isValid(record.toString(), target) && findNext(record, target))
        return true

    record[idx] = '.'
    if (isValid(record.toString(), target) && findNext(record, target))
        return true

    record[idx] = '?'
    return false

}


fun createGroups(line : String) : List<Int>{

    val match = "#+".toRegex()

    return match.findAll(line).map{it.value.length}.toList()

}

fun isValid(line: String, groups: List<Int>) : Boolean{

    val part = createGroups(line.split("?")[0])

    if (part.isEmpty())
        return true

    for (i in part.indices - 1) {
        if (part[i] != groups[i])
            return false
    }

    return part.last() <= groups[part.size - 1]
}




