package lifeGame

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Cell(val x: Int, val y: Int) {
    var isAlive: Boolean = false
    var coords = "$x|$y"
}

class Field {
    var fieldXY: MutableList<List<Cell>> = mutableListOf()
    fun generateFieldSquare(size: Int): MutableList<List<Cell>> {
        val field: MutableList<List<Cell>> = mutableListOf()
        for (i in 0..size) {
            val list: MutableList<Cell> = mutableListOf()
            for (j in 0..size) {
                list.add(Cell(i, j))
            }
            field.add(list)
        }
        return field
    }

    fun setField(field: MutableList<List<Cell>>) {
        fieldXY = field
    }

    fun getNeighbours(Y: Int, X: Int): List<Cell> {
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

    fun getAliveNeighboursCount(Y: Int, X: Int): Int {
        var result = 0
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
        if (fieldXY[y(-1)][x(-1)].isAlive) result += 1
        if (fieldXY[y(-1)][x(0)].isAlive) result += 1
        if (fieldXY[y(-1)][x(1)].isAlive) result += 1
        if (fieldXY[y(0)][x(-1)].isAlive) result += 1
        if (fieldXY[y(0)][x(1)].isAlive) result += 1
        if (fieldXY[y(1)][x(-1)].isAlive) result += 1
        if (fieldXY[y(1)][x(0)].isAlive) result += 1
        if (fieldXY[y(1)][x(1)].isAlive) result += 1
        return result
    }
}

fun calculateTour(f: Field) {
    val nextField: MutableList<List<Cell>> = f.generateFieldSquare(f.fieldXY.size-1)
    f.fieldXY.forEach { list ->
        list.forEach {
            val aliveCellsNearby = f.getAliveNeighboursCount(it.x, it.y)
            if (!it.isAlive) {
                if (aliveCellsNearby == 3) {
                    nextField[it.y][it.x].isAlive = true
                }
            }
            if (it.isAlive) {
                nextField[it.y][it.x].isAlive = aliveCellsNearby in 2..3
                println(aliveCellsNearby in 2..3)
            }
        }
    }
    f.setField(nextField)
}

fun checkAlive(f: Field): Boolean = f.fieldXY.any {
    it.any { cell ->
        cell.isAlive
    }
}

fun render(f: Field) {
    f.fieldXY.forEach {
        it.forEach { cell ->
            if (cell.isAlive) print("[X]") else print("[ ]")
        }
        println("")
    }
    println("")
}

fun step(f: Field) = runBlocking {
    launch {
        while (checkAlive(f)) {
            delay(500)
            render(f)
            calculateTour(f)
        }
        delay(3000)
        println("finished")

    }
}

fun main() {
    val s = Field()
    s.setField(s.generateFieldSquare(10))
    //setting up the figure R-pentamino
//    s.fieldXY[3][2].isAlive = true
//    s.fieldXY[3][3].isAlive = true
//    s.fieldXY[2][4].isAlive = true
//    s.fieldXY[2][3].isAlive = true
//    s.fieldXY[1][3].isAlive = true
    //glider
    s.fieldXY[3][2].isAlive = true
    s.fieldXY[3][3].isAlive = true
    s.fieldXY[3][4].isAlive = true
    s.fieldXY[2][4].isAlive = true
    s.fieldXY[1][3].isAlive = true
    step(s)
}