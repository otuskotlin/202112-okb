package ru.otus.otuskotlin.dsl


// Operator overloading
////////////////////////////////////////////////////////////////////////////////

/**
 * Add simple, clean and usefully methods to point with overloading operator
 */
data class Point(val x: Int, val y: Int) {
    fun sumSimple(another: Point): Point {
        return Point(x + another.x, y + another.y)
    }

    infix fun sum(another: Point): Point {
        return Point(x + another.x, y + another.y)
    }

    operator fun plus(another: Point): Any {
        return Point(x + another.x, y + another.y)
    }

    operator fun minus(another: Point): Any {
        return Point(x - another.x, y - another.y)
    }

    operator fun div(value: Int): Any {
        return Point(x / value, y / value)
    }
}


fun main() {
    // Sample with pairs
    val a = Point(1, 2)
    val b = Point(1, 2)

    val sumSimple = a.sumSimple(b)
    println(sumSimple)

    val sum = a sum b
    println(sum)

    val sumOp = a + b
    println(sumOp)

    val minusOp = a - b
    println(minusOp)

    val divOp = a / 2
    println(divOp)
}

