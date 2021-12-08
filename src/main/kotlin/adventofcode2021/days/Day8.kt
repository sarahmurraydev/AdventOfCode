package adventofcode2021.days

import adventofcode2021.getDataFromFileAsStringList

// https://adventofcode.com/2021/day/8

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
    val day8Ex = splitData(day8exData)
    val day8Data = splitData(getDataFromFileAsStringList(8))
    convertLettersToSize(day8Ex)
    convertLettersToSize(day8Data)
}

fun splitData(data: List<String>): List<List<String>> {
    return data.map { it.split("| ").last().split(" ") }
}


val numSegments = listOf(6, 2, 5, 5, 4, 5, 6, 3, 7, 6)
// where numSegments[0] = 6 means to show the number 0, 6 segments need to be lit

fun convertLettersToSize(data: List<List<String>>) {
    var counts = 0 // mutableListOf<List<Int>>()
    for (d in data.indices) {
        // counts.add(data[d].map { it.length })
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