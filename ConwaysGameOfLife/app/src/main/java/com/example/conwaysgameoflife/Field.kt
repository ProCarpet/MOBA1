    package com.example.conwaysgameoflife

    import android.graphics.Canvas
    import kotlin.random.Random

    class Field {

        val row: Int
        val col: Int
        var oldField: Array<BooleanArray>
        var newField: Array<BooleanArray>

        constructor(row: Int, col: Int) {
            this.row = row
            this.col = col
            oldField = Array(col) { BooleanArray(row) }
            newField = Array(col) { BooleanArray(row) }
            // fill randomly
            for (i in 0 until this.col) {
                for (x in 0 until this.row) {
                    oldField[i][x] = Random.nextBoolean()
                }
            }
        }

        init {
            for(i in 0  until  10000){
                update()
                printToConsole()

            }
        }

        fun update() {
            var tempField: Array<BooleanArray> = newField

            for (i in 0 until col) {
                for (x in 0 until row) {
                    val currentState: Boolean = oldField[col][row]
                    val liveAdjacentCells: Int = getLiveAdjacent(i, x)
                    if (currentState && (liveAdjacentCells == 2 || liveAdjacentCells == 3)) {
                        //stay alive
                        newField[i][x] = true;
                    } else if (!currentState && liveAdjacentCells == 3) {
                        //become alive
                        newField[i][x] = true;
                    } else {
                        //die :)
                        newField[i][x] = false;
                    }
                }

            }
            oldField = tempField
        }

        fun printToConsole() {
            for (i in 0 until this.col) {
                print("-")
                for (x in 0 until this.row) {
                    var symbol = " "
                    if (oldField[i][x]) symbol = "x"
                    print("|" + symbol)
                }
                println()
            }
        }

        fun draw(canvas: Canvas) {

        }

        fun getLiveAdjacent(col: Int, row: Int): Int {
            var alliveNeighbors: Int = 0;
            // step to right and then counter clockwise check
            //  |   |
            //--------
            //  |   | x
            //--------
            //  |   |
            if (isInRange(col + 1, row)) {
                if (oldField[col + 1][row]) alliveNeighbors++;
            }
            //  |   | x
            //--------
            //  |   |
            //--------
            //  |   |
            if (isInRange(col + 1, row - 1)) {
                if (oldField[col + 1][row - 1]) alliveNeighbors++;
            }
            //  | X |
            //--------
            //  |   |
            //--------
            //  |   |
            if (isInRange(col, row - 1)) {
                if (oldField[col][row - 1]) alliveNeighbors++;
            }
            //x |   |
            //--------
            //  |   |
            //--------
            //  |   |
            if (isInRange(col - 1, row - 1)) {
                if (oldField[col - 1][row - 1]) alliveNeighbors++;
            }
            //  |   |
            //--------
            //x |   |
            //--------
            //  |   |
            if (isInRange(col - 1, row)) {
                if (oldField[col - 1][row]) alliveNeighbors++;
            }
            //  |   |
            //--------
            //  |   |
            //--------
            //x |   |
            if (isInRange(col - 1, row + 1)) {
                if (oldField[col - 1][row + 1]) alliveNeighbors++;
            }
            //  |   |
            //--------
            //  |   |
            //--------
            //  | x |
            if (isInRange(col, row + 1)) {
                if (oldField[col][row + 1]) alliveNeighbors++;
            }
            //  |   |
            //--------
            //  |   |
            //--------
            //  |   | x
            if (isInRange(col + 1, row + 1)) {
                if (oldField[col + 1][row + 1]) alliveNeighbors++;
            }
            return alliveNeighbors
        }

        fun isInRange(col: Int, row: Int): Boolean {
            return col < this.col && row < this.row && row >= 0 && col >= 0
        }

    }
