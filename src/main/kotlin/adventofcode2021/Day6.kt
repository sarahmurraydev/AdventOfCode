package adventofcode2021

import kotlin.math.pow

fun main() {
    println("=====================")
    println("Advent of Code Day 5!")
    val convertedData = convertDataStringToInt(exData)
    val days = 256
    //println("After $days days we have ${haveFish(days, convertedData)}")
    //println("After $days days we have ${expoRate(days, convertedData.size, (1.toFloat())/(9.toFloat()))} fish")
    countNewFish(convertedData, days)
}

const val exData = "3,4,3,1,2"
const val realData = "5,1,2,1,5,3,1,1,1,1,1,2,5,4,1,1,1,1,2,1,2,1,1,1,1,1,2,1,5,1,1,1,3,1,1,1,3,1,1,3,1,1,4,3,1,1,4,1,1,1,1,2,1,1,1,5,1,1,5,1,1,1,4,4,2,5,1,1,5,1,1,2,2,1,2,1,1,5,3,1,2,1,1,3,1,4,3,3,1,1,3,1,5,1,1,3,1,1,4,4,1,1,1,5,1,1,1,4,4,1,3,1,4,1,1,4,5,1,1,1,4,3,1,4,1,1,4,4,3,5,1,2,2,1,2,2,1,1,1,2,1,1,1,4,1,1,3,1,1,2,1,4,1,1,1,1,1,1,1,1,2,2,1,1,5,5,1,1,1,5,1,1,1,1,5,1,3,2,1,1,5,2,3,1,2,2,2,5,1,1,3,1,1,1,5,1,4,1,1,1,3,2,1,3,3,1,3,1,1,1,1,1,1,1,2,3,1,5,1,4,1,3,5,1,1,1,2,2,1,1,1,1,5,4,1,1,3,1,2,4,2,1,1,3,5,1,1,1,3,1,1,1,5,1,1,1,1,1,3,1,1,1,4,1,1,1,1,2,2,1,1,1,1,5,3,1,2,3,4,1,1,5,1,2,4,2,1,1,1,2,1,1,1,1,1,1,1,4,1,5"

fun convertDataStringToInt(data: String): MutableList<Int> {
    val splitData = data.split(",")

    return splitData.map {
        it.toInt()
    }.toMutableList()
}

/**
 * Brute force for part 1 -- does not work for part 2
 */
fun haveFish(days: Int, fish: MutableList<Int>): Int {
    var daysToGo = days
    var numFish = fish.size
    while (daysToGo > 0) {
        for (f in fish.indices) {
            if (fish[f] > 0) {
                fish[f] -= 1
            } else {
                //println("fish $f had a fish!")
                // fish[f] = 0
                fish[f] = 6
                fish.add(8)
                numFish += 1
            }
        }
        daysToGo -= 1
    }

    return numFish
}

fun expoRate(days: Int, initial: Int, growthRate: Float): Float {
    println("Figuring out number of fish after $days starting with $initial, and growing at a rate of $growthRate")
    println(1 + growthRate)
    println((1 + growthRate).pow(days))
    return initial * (1 + growthRate).pow(days)
}

fun countNewFish(fish: MutableList<Int>, days: Int) {
    // make an array were fishTimer[0] == fish about to spawn
    val fishTimer = MutableList(9) { 0 }
    for (f in fish.indices) {
        // fish[f] == days left until fish spawns
        fishTimer[fish[f]] += 1
    }

    println("initial fish timer: $fishTimer")

    var daysToGo = days
    while (daysToGo > 0) {
        // check how many fish are spawning today
        // Recall: every day X fish have timer of 0, the next day those X fish have timer of 6 & there are X new fish with timer of 8
        val fishBornToday = fishTimer[0]
        if (days - daysToGo > 245) println("At day ${days - daysToGo} the count is $fishTimer")
        fishTimer.removeAt(0) // pop off the fish about to spawn so fish shift down 1 day
        // then add the new fish because they don't start tracking today
        if (days - daysToGo > 245)  println("there are $fishBornToday fish born today! Happy birthday :D ")
        if (days - daysToGo > 247) {
            println("===================")
            println("Number of fish on day 6: ${fishTimer[6]}")
            println("Number of fish born today: ${fishBornToday}")
            println("New number of fish on day 6: ${fishTimer[6] + fishBornToday}")
            println("New number of fish on day 6: ${1066366882 + 1263344510}")
            println("===================")
        }
        fishTimer[6] += fishBornToday
        fishTimer.add(8, fishBornToday)
        daysToGo -= 1
    }

    println(fishTimer)
    println("After $days, there are ${fishTimer.sum()} fish")
}


// Scratch // Notes

// every 7 days, existing lantern fish has new lantern fish
// you can figure out after X days, existing lantern fish should have had:
// y = (x - b)/7
// where b is the current timer of the lanternfish
// for new fish, b = 8

// every day X fish have timer of 0, the next day those X fish have timer of 6 & there are X new fish with timer of 8

// part 2 -- my brute approach to part 1 doesn't work -- runs out of heap space
// let's think back to equation
// expontential grow
// I have three points (days, fish) ==> (0, 5), (18, 26), (80, 5934)
// expo growth is f(x)=a(1+r)^{x}
// where a = initial amount = 5
// r = growth rate (1/7) or (1/8)
// x = days
// f(x) = 5(1 + (1/7))^(18)

// find growth rate
// f(x)=a(1+r)^{x}
// ==> r = [f(x)/a]^(1/x) - 1
// (18, 26) ==> r = 23.3333%
// (80, 5934) ==> r = 1482.2500%

// part2: got 1214653763 -- too low