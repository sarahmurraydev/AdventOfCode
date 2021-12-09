package adventofcode2021.days

import adventofcode2021.getDataFromFileAsStringList

// https://adventofcode.com/2021/day/8

val smallerExData = listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")

val day8exData = listOf(
 "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
 "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
 "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
 "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
 "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
 "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
 "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
 "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
 "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
 "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
)

fun main() {
    val day8ExP1 = splitDataPart1(day8exData)
    val day8ExP2 = splitDataPart2(smallerExData)
    val day8Data = splitDataPart1(getDataFromFileAsStringList(8))
    //convertLettersToSize(day8ExP1)
    convertLettersToSize(day8ExP2)
    //convertLettersToSize(day8Data)
}

fun splitDataPart1(data: List<String>): List<List<String>> {
    return data.map { it.split("| ").last().split(" ") }
}

fun splitDataPart2(data: List<String>): List<List<String>> {
    return data.map { it.split(" | ").first().split(" ") }
}


val numSegments = listOf(6, 2, 5, 5, 4, 5, 6, 3, 7, 6)
// where numSegments[0] = 6 means to show the number 0, 6 segments need to be lit
// 0 --> 6 , 1 --> 2, 2 --> 5, 3 --> 5, 4 --> 4, 5 --> 5, 6 --> 6, 7 --> 3, 8 --> 7, 9 --> 6

fun convertLettersToSize(data: List<List<String>>) {
    var counts = 0 // mutableListOf<List<Int>>()
    for (d in data.indices) {
        // counts.add(data[d].map { it.length })
        mapLettersToSegments(data[d])
        for (str in data[d]) {
            if (checkLengthIsUnique(str)) counts += 1
        }
    }
    println("There are $counts times 1, 3, 7, or 8 appear in this data")
}

fun checkLengthIsUnique(str: String): Boolean {
    return when (str.length) {
        // aka digits 1, 7, 4, or 8 appear
        2, 3, 4, 7 -> true
        else -> false
    }
}

// part two -- need to figure out what each letter maps to, then get the digit after "|"
// want to get two segments in one
// then get three segments in 7, segment not also in one = top
// to find 6, find the str of size 6 that does not have both letters contained in 1
//
/**
 * Map a letter [a,g] to a segment of the 7-segment light
 * (clockwise: top, topRight, bottomRight, bottom, bottomLeft, middle, topLeft)
 */

// set the variables for the segments, they will be assigned later
var top = ""
var topRight = ""
var bottomRight = ""
var bottom = ""
var bottomLeft = ""
var middle = ""
var topLeft = ""

fun mapLettersToSegments(data: List<String>) {
    val strCountSorted = data.sortedBy { it.length }
    println(strCountSorted)

    // get unique digits: strCountSorted = [2, 3, 4, 5, 5, 5, 6, 6, 6, 7]
    val oneSegments = strCountSorted.first()
    val sevenSegments = strCountSorted[1] // value = 3
    val fourSegments = strCountSorted[3] // value = 4
    val eightSegments = strCountSorted.last() // value = 7
    val fivePartDigits = strCountSorted.filter { it.length == 5  }
    val sixPartDigits = strCountSorted.filter { it.length == 6 }

    // the top segment is the segment in seven, but not in one
    top = findTopSegment(oneSegments, sevenSegments)

    // the bottomRight segment is the segment in six and one
    // six is the the str of size 6 that does not contain both elements of one
    var sixSegments = findRightAndSixSegments(oneSegments, sixPartDigits)


    printSegments()
}


fun findTopSegment(one: String, seven: String): String {
    val firstOfOne = one.first().toString()
    val secondOfOne = one.last().toString()
    return seven.replace(firstOfOne, "").replace(secondOfOne, "")
}

fun findRightAndSixSegments(one: String, piecesWithSixLetters: List<String>): String {
    val sixthPiece = piecesWithSixLetters.filter {
        println("$it: ${!(it.contains(one.first()) && it.contains(one.last()))}")
        !(it.contains(one.first()) && it.contains(one.last()))
    } // 6 does not have both segments of one
    // should leave one element == 6
    val segSix = sixthPiece.first()

    if (segSix.contains(one.first())) {
        // if segment six contains the first element of one, then the bottom right element is that element
        bottomRight = one.first().toString()
        topRight = one.last().toString()
    } else {
        // second element exists in segSix meaning it's the bottomRight element
        bottomRight = one.last().toString()
        topRight = one.first().toString()
    }

    return segSix
}

fun printSegments() {
    println("the top is $top")
    println("the top right is $topRight")
    println("the bottom right is $bottomRight")
    println("the bottom is $bottom")
    println("the left bottom is $bottomLeft")
    println("the middle is $middle")
    println("the left top is $topLeft")
}