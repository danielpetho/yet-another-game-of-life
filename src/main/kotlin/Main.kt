package kproc.life

import processing.core.PApplet
import kotlin.random.Random

class GameOfLife() : PApplet() {
    var run: Boolean = true
    var resolution: Int = 5
    var cols: Int = 0
    var rows: Int = 0
    var grid: Array<IntArray> = arrayOf(intArrayOf(0), intArrayOf(0))

    var buttonStop = Button(this, "stop", 10, 10+30+5,  90, 30)
    var buttonStart = Button(this,"start",10, 10, 90, 30)
    var buttonRandomize = Button(this, "randomize", 10, 10+30*2+10, 90, 30)
    var buttonEmpty = Button(this, "empty grid", 10, 10+30*3+15, 90, 30)

    init {
        setup()
    }
    override fun settings() {
        size(1200, 700)
    }

    override fun setup() {
        cols = width / resolution
        rows = height / resolution

        grid = randomGrid(cols, rows)
    }

    override fun draw() {
        background(20)

        renderGrid()

        if(run) {

            var next = emptyGrid(cols, rows)

            for (i in 0..cols - 1) {
                for (j in 0..rows - 1) {

                    var state = grid[i][j]
                    var sum = 0
                    var neighbors = countNeighbors(grid, i, j)

                    if (state == 0 && neighbors == 3) {
                        next[i][j] = 1
                    } else if (state == 1 && (neighbors < 2 || neighbors > 3)) {
                        next[i][j] = 0
                    } else {
                        next[i][j] = state
                    }
                }
            }

            grid = next
        }

        buttonStart.draw()
        buttonStop.draw()
        buttonRandomize.draw()
        buttonEmpty.draw()
    }

    fun renderGrid() {
        for (i in 0..cols - 1) {
            for (j in 0..rows - 1) {
                var x = i * resolution
                var y = j * resolution
                if(grid[i][j] == 1) {
                    fill(240f, 240f, 230F)
                    ellipse(x, y, resolution, resolution)
                    //rect(x, y, resolution, resolution)
                }
            }
        }
    }

    fun emptyGrid(cols: Int, rows: Int): Array<IntArray>  {
        var grid: Array<IntArray> = Array(cols) { IntArray(rows) { j -> 0 } }
        return grid
    }

    fun randomGrid(cols: Int, rows: Int): Array<IntArray> {
        var grid: Array<IntArray> = Array(cols) { IntArray(rows) { j -> Random.nextInt(2) } }
        return grid
    }

    fun countNeighbors(grid: Array<IntArray>, x: Int, y: Int): Int {
        var sum = 0
        for (i in -1..1) {
            for (j in -1..1) {

                var col = (x + i + cols) % cols
                var row = (y + j + rows) % rows
                sum += grid[col][row]
            }
        }

        sum -= grid[x][y]
        return sum
    }

    override fun mousePressed() {
        super.mousePressed()
        if(buttonStart.overButton()) {
            run = true
        }

        if(buttonStop.overButton()) {
            run = false
        }

        if(buttonRandomize.overButton()) {
            grid = randomGrid(cols, rows)
        }

        if(buttonEmpty.overButton()) {
            grid = emptyGrid(cols, rows)
            run = false
        }

    }

    override fun mouseDragged() {
        super.mouseDragged()
        if(mouseX > 0 && mouseX < width && mouseY > 0 && mouseY < height) {
            var ii = floor((mouseX / resolution).toFloat())
            var jj = floor((mouseY / resolution).toFloat())
            grid[ii][jj] = 1
        }


    }
}

fun main(args: Array<String>) {
    PApplet.main("kproc.life.GameOfLife")
}