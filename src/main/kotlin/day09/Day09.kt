package day09

import java.io.File

fun main() {


    val input = readInput(File("src/main/kotlin/day09/input.txt"))

    var sum01 = 0
    var sum02 = 0

    for (line in input) {

        val sequences : List<MutableList<Int>> = getSequences(line)

        for (i in sequences.indices.reversed()) {

            val sequence = sequences[i]
            if (i == sequences.size - 1) {
                sequence.add(0)
                sequence.add(0,0)
            }
            else{
                sequence.add(sequence.last() + sequences[i + 1].last())
                sequence.add(0, sequence.first() - sequences[i + 1].first())
            }


        }

        sum01 += sequences[0].last()
        sum02 += sequences[0].first()


    }

    println("Part01 sum: $sum01")
    println("Part02 sum: $sum02")

}

fun getSequences(startSeq : List<Int>)  : List<MutableList<Int>> {

    var sequence : MutableList<Int> = mutableListOf()
    sequence.addAll(startSeq)

    val result : MutableList<MutableList<Int>> = mutableListOf()
    result.add(sequence)


    var allZeros = false
    while (!allZeros) {

        val diffs : MutableList<Int> = mutableListOf()
        for (i in 0 ..< sequence.size - 1) {
            diffs.add(sequence[i+1] - sequence[i] )
        }

        sequence = diffs
        result.add(sequence)

        allZeros = true
        for (n in sequence) {
            if (n != 0) {
                allZeros = false
                break
            }

        }

    }

    return result

}

fun readInput(file: File) : List<List<Int>> {
    val result : MutableList<List<Int>> = mutableListOf()

    file.bufferedReader().forEachLine {line ->
        result.add(line.split(" ").map {it.toInt()})
    }

    return result


}