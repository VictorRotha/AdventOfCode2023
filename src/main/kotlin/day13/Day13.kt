package day13

import java.io.File
import java.lang.StringBuilder

fun main() {

    val file = File("src/main/kotlin/day13/input.txt")

    var pattern : MutableList<String> = mutableListOf()

    var resultPartOne = 0
    var resultPartTwo = 0

    file.bufferedReader().forEachLine {line ->

        if (line.isEmpty()) {
            resultPartOne += findNewMirror(pattern, -1, -1)
            resultPartTwo += processPatternPartTwo(pattern)
            pattern = mutableListOf()
            return@forEachLine
        }

        pattern.add(line)

    }

    if (pattern.isNotEmpty()) {
        resultPartOne += processPatternPartOne(pattern)
        resultPartTwo += processPatternPartTwo(pattern)
    }


    println("Part 01: $resultPartOne")
    println("Part 02: $resultPartTwo")


}

fun processPatternPartOne(pattern: MutableList<String>) = findNewMirror(pattern, -1, -1)


fun processPatternPartTwo(pattern: MutableList<String>): Int {

    for (row in pattern.indices)
        for (col in pattern[row].indices) {

            val newPattern = pattern.map { it.toCharArray() }

            newPattern[row][col] = if (newPattern[row][col] == '.') '#' else '.'

            val oldH = findNewHorizontalMirror(pattern, -1)
            val oldV = findNewVerticalMirror(pattern, -1)
            val temp = findNewMirror(newPattern.map {String(it)}, oldH, oldV)

            if (temp != -1) {
                return temp
            }

        }

    return -1

}


fun findNewMirror(pattern: List<String>, oldH: Int, oldV: Int) : Int {

    val h = findNewHorizontalMirror(pattern, oldH)

    return if (h != -1 ) h * 100  else  findNewVerticalMirror(pattern, oldV)

}


fun findNewVerticalMirror(pattern: List<String>, old : Int) : Int {

    var isMirror : Boolean

    val w = pattern[0].length

    for (i in 0 ..< w - 1) {

        val key = getColKey(i, pattern)
        val nextKey = getColKey(i + 1, pattern)

        if (key == nextKey && i+1 != old) {

            val range = i.coerceAtMost(w - i - 2)

            isMirror = true
            for (j in 1 .. range) {
                if (getColKey(i - j, pattern) != getColKey(i + 1 + j, pattern)) {
                    isMirror = false
                    break
                }
            }

            if (isMirror) {
                return(i + 1)

            }
        }
    }

    return -1
}


fun getColKey(col: Int, pattern: List<String>) : String {
    val sb = StringBuilder()
    for (row in pattern) {
        sb.append(row[col])
    }
    return sb.toString()
}


fun findNewHorizontalMirror(pattern: List<String>, old: Int) : Int {

    var isMirror : Boolean
    for (i in 0 ..< pattern.size - 1) {

        val line = pattern[i]
        val nextLine = pattern[i + 1]

        if (line == nextLine && i + 1 != old) {

            val range = i.coerceAtMost(pattern.size - i - 2)

            isMirror = true
            for (j in 1 .. range) {
                if (pattern[i - j] != pattern[i + 1 + j]) {
                    isMirror = false
                    break
                }
            }

            if (isMirror) {
                return i + 1
            }

        }

    }

    return -1

}

