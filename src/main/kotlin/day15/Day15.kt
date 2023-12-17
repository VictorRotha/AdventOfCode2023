package day15

import java.io.File

fun main() {


    val file = File("src/main/kotlin/day15/input.txt")
    val input = file.bufferedReader().readLine().split(",")

    partOne(input)
    partTwo(input)

}


fun partTwo(input: List<String>) {

//    244199

    val boxes: MutableList<MutableList<String>> = mutableListOf()
    for (i in 0..255)
        boxes.add(mutableListOf())

    for (line in input) {

        val label = line.split(Regex("[=-]"))[0]
        val boxNumber = hash(label)
        val op = line.substring(label.length, label.length + 1)
        val focal = if (op == "=") line.split(Regex("[=-]"))[1] else null

        val box = boxes[boxNumber]

        if (op == "=") {

            var isInBox = false
            for (i in box.indices) {
                if (box[i].startsWith(label)) {
                    isInBox = true
                    box[i] = "$label $focal"
                    break
                }
            }

            if (!isInBox)
                boxes[boxNumber].add("$label $focal")

        } else {

            for (i in box.indices) {
                if (box[i].startsWith(label)) {
                    boxes[boxNumber].removeAt(i)
                    break
                }
            }
        }

    }

    println("Part 02: ${getPowers(boxes)}")
}

fun partOne(input: List<String>) {

    var sum = 0
    for (line in input)
        sum += hash(line)

    println("Part 01: $sum")

}


fun getPowers(boxes : List<List<String>>) : Int {

    var total = 0

    for (boxNumber in boxes.indices) {

        for (i in boxes[boxNumber].indices) {

            val focal = boxes[boxNumber][i].split(" ")[1].toInt()

            val power = (boxNumber + 1) * (i + 1) * focal

            total += power


        }

    }

    return total

}


fun hash(input : String) : Int {

    var result = 0

    for (c in input) {
        result += c.code
        result *= 17
        result = result.mod(256)
    }

    return result
}