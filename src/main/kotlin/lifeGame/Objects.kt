package lifeGame

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Cell(val x: Int, val y: Int) {
    var isAlive: Boolean = false
    var coords = "$x|$y"
}

class Field {
    val fieldXY: List<List<Cell>> = listOf(
        listOf(Cell(0, 0), Cell(0, 1), Cell(0, 2), Cell(0, 3), Cell(0, 4)),
        listOf(Cell(1, 0), Cell(1, 1), Cell(1, 2), Cell(1, 3), Cell(1, 4)),
        listOf(Cell(2, 0), Cell(2, 1), Cell(2, 2), Cell(2, 3), Cell(2, 4)),
        listOf(Cell(3, 0), Cell(3, 1), Cell(3, 2), Cell(3, 3), Cell(3, 4)),
        listOf(Cell(4, 0), Cell(4, 1), Cell(4, 2), Cell(4, 3), Cell(4, 4))
    )

    fun getNeighbours(X: Int, Y: Int): List<Cell> {
        val result = mutableListOf<Cell>()
        fun x(arg: Int): Int {
            if (X + arg >= fieldXY.size) return 0
            return if (X + arg < 0) fieldXY.size - 1
            else X + arg
        }
        fun y(arg: Int): Int {
            if (Y + arg >= fieldXY.size) return 0
            return if (Y + arg < 0) fieldXY.size - 1
            else Y + arg
        }
        result.add(fieldXY[y(-1)][x(-1)])
        result.add(fieldXY[y(-1)][x(0)])
        result.add(fieldXY[y(-1)][x(1)])
        result.add(fieldXY[y(0)][x(-1)])
        result.add(fieldXY[y(0)][x(1)])
        result.add(fieldXY[y(1)][x(-1)])
        result.add(fieldXY[y(1)][x(0)])
        result.add(fieldXY[y(1)][x(1)])
        return result
    }
}

fun calculateTour(f: Field) {
    return f.fieldXY.forEach { list ->
        list.forEach {
            if (f.getNeighbours(it.x, it.y).filter { cell ->
                    cell.isAlive
                }.size > 3 || f.getNeighbours(it.x, it.y).filter { cell ->
                    cell.isAlive
                }.size < 2) {
                it.isAlive = false
            }
            if (f.getNeighbours(it.x, it.y).filter { cell ->
                    cell.isAlive
                }.size == 3) {
                it.isAlive = true
            }
        }
    }
}

fun checkAlive(f: Field): Boolean = f.fieldXY.any {
    it.any { cell ->
        cell.isAlive
    }
}

fun render(f: Field) {
    f.fieldXY.forEach {
        it.forEach { cell ->
            if (cell.isAlive) print(" [X] ") else print(" [ ] ")
        }
        println("\n")
    }
}

fun step(f: Field) = runBlocking {
    launch {
        while (checkAlive(f)) {
            delay(500)
            calculateTour(f)
            render(f)
        }
        delay(3000)
        println("finished")

    }
}

fun main() {
    val s = Field()
    //setting up the figure
    s.fieldXY[3][2].isAlive = true
    s.fieldXY[3][3].isAlive = true
    s.fieldXY[2][4].isAlive = true
    s.fieldXY[1][4].isAlive = true
    s.fieldXY[0][3].isAlive = true
    s.fieldXY[1][2].isAlive = true
    s.fieldXY[2][1].isAlive = true
    step(s)
}