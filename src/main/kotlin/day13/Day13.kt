package day13

import java.io.File
import java.lang.StringBuilder

fun main() {

    val file = File("src/main/kotlin/day13/input.txt")

    partOne(file)


}

fun partOne(file: File) {

    var pattern : MutableList<String> = mutableListOf()

    var result = 0

    file.bufferedReader().forEachLine {line ->

        if (line.isEmpty()) {
            result += processPattern(pattern)
            pattern = mutableListOf()
            return@forEachLine
        }

        pattern.add(line)

    }

    if (pattern.isNotEmpty())
        result += processPattern(pattern)

    println("Part 01: $result")

}

fun processPattern(pattern : List<String>) : Int {

    var result = 0

    val h = findHorizontalMirror(pattern)

    if (h != -1 )
        result += h * 100
    else
        result = findVerticalMirror(pattern)

    return result
}

fun findVerticalMirror(pattern: List<String>) : Int {
    var isMirror : Boolean

    val w = pattern[0].length

    for (i in 0 ..< w - 1) {

        val key = getColKey(i, pattern)
        val nextKey = getColKey(i + 1, pattern)

        if (key == nextKey) {

            val range = i.coerceAtMost(w - i - 2)

            isMirror = true
            for (j in 1 .. range) {
                if (getColKey(i - j, pattern) != getColKey(i + 1 + j, pattern)) {
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

fun getColKey(col: Int, pattern: List<String>) : String {
    val sb = StringBuilder()
    for (row in pattern) {
        sb.append(row[col])
    }
    return sb.toString()
}

fun findHorizontalMirror(pattern: List<String>) : Int {

    var isMirror : Boolean
    for (i in 0 ..< pattern.size - 1) {

        val line = pattern[i]
        val nextLine = pattern[i + 1]

        if (line == nextLine) {

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
