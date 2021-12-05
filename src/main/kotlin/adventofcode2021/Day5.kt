import adventofcode2021.getDataFromFileAsStringList

// need to find all places where two or more lines intersect
// need to "draw" each line by adding 1s to matrix of 0s
// aoc-2021-in-kotlin

// if you get the slope of the line
// y2 = (y2 - y1)/(x2-x)x2 + b
// then you can add all the points between (a, b) and (c, d)

fun main() {
    val (firstP, secondP) = turnDataIntoPoints(exData)
    for(p in firstP.indices) {
        plotPointsOnLine(firstP[p], secondP[p])
    }
}

val exData = mutableListOf<String>(
    "0,9 -> 5,9",
    "8,0 -> 0,8",
    "9,4 -> 3,4",
    "2,2 -> 2,1",
    "7,0 -> 7,4",
    "6,4 -> 2,0",
    "0,9 -> 2,9",
    "3,4 -> 1,4",
    "0,0 -> 8,8",
    "5,5 -> 8,2"
)

val day5Data = getDataFromFileAsStringList(5).toMutableList()

fun turnDataIntoPoints(data: MutableList<String>): Pair<MutableList<Point>, MutableList<Point>> {
    val firstPoints = mutableListOf<Point>()
    val secondPoints = mutableListOf<Point>()
    val dataNoArrows = data.map { it.split(",") }
    for (line in dataNoArrows) {
        val x1 = line[0].toInt()
        val y1AndX2 = line[1].split(" -> ")
        val y1 = y1AndX2[0].toInt()
        val x2 = y1AndX2[1].toInt()
        val y2 = line[2].toInt()
        firstPoints.add(Point(x1, y1))
        secondPoints.add(Point(x2, y2))
    }
    return Pair(firstPoints, secondPoints)
}

data class Point(val x: Int, val y: Int)

fun getSlope(point1: Point, point2: Point): Int? {
    return if (point2.x - point1.x == 0) null
    else (point2.y - point1.y) / (point2.x - point1.x)
}

val grid = MutableList(10) { MutableList(10) { 0 } }
//val grid = MutableList(1000) { MutableList(1000) { 0 } }
var countIntersections = 0

fun plotPointsOnLine(point1: Point, point2: Point) {
    println("plotting points: $point1, $point2")
    // only care about horizontal or vertical lines part 1
    when (getSlope(point1, point2)) {
        null -> plotVerticalLines(point1, point2)
        0 -> plotHorizontalLines(point1, point2)
        1 -> plotDiagonalLines(point1, point2)
    }
    println(printGrid(grid))
    println("There are now $countIntersections intersections")
}

fun printGrid(grid: MutableList<MutableList<Int>>) {
    println("| ========== |")
    grid.forEach {
        print("${it}\n")
    }
    println("| ========== |")
}

/// part 1: first try got 4524 -- too low
/// part 1 right answer: 6311

/// part 2 -- also want to plot lines that have slope of 1
fun plotDiagonalLines(point1: Point, point2: Point) {
    println("need to print a diagonal line")
}

fun plotVerticalLines(point1: Point, point2: Point) {
    println("    horizontal line")
    // find out how many points you need to plot
    var ySpread: Int
    var startingPoint: Point
    if(point2.y > point1.y) {
        ySpread = point2.y - point1.y
        startingPoint = point1
    } else {
        ySpread = point1.y - point2.y
        startingPoint = point2
    }

    for(y in 0..ySpread) {
        println("        setting grid point (${startingPoint.x}, ${startingPoint.y + y})")
        println("        previous count at point: ${grid[startingPoint.y + y][startingPoint.x]}")
        grid[startingPoint.y + y][startingPoint.x] += 1
        println("        new count at point: ${grid[startingPoint.y + y][startingPoint.x]}")
        // if at least 2 lines hit this point, add to intersection count
        // only do "==2" not ">=2" so we only count each intersection once
        if (grid[startingPoint.y + y][startingPoint.x] == 2) {
            println("    increment intersections")
            countIntersections += 1
        }
    }
}

fun plotHorizontalLines(point1: Point, point2: Point) {
    println("    vertical line")
    var xSpread: Int
    var startingPoint: Point
    if(point2.x > point1.x) {
        xSpread = point2.x - point1.x
        startingPoint = point1
    } else {
        xSpread = point1.x - point2.x
        startingPoint = point2
    }

    for(x in 0..xSpread) {
        println("        setting grid point (${startingPoint.x + x}, ${startingPoint.y})")
        println("previous count at point: ${grid[startingPoint.y][startingPoint.x + x] }")
        grid[startingPoint.y][startingPoint.x + x] += 1
        println("        new count at point: ${grid[startingPoint.y][startingPoint.x + x]}")
        if(grid[startingPoint.y][startingPoint.x + x] == 2) {
            println("        increment intersections")
            countIntersections += 1
        }
    }
}