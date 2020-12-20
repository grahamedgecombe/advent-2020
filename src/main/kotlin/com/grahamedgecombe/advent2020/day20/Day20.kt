package com.grahamedgecombe.advent2020.day20

import com.grahamedgecombe.advent2020.Puzzle
import com.grahamedgecombe.advent2020.UnsolvableException
import java.util.BitSet
import kotlin.math.sqrt

object Day20 : Puzzle<List<Day20.Tile>>(20) {
    sealed class Tile(val id: Int, val width: Int, val height: Int) {
        class Base(id: Int, width: Int, height: Int, private val pixels: BitSet) : Tile(id, width, height) {
            override fun get(x: Int, y: Int): Boolean {
                require(x in 0 until width && y in 0 until height)
                return pixels[y * width + x]
            }

            override fun countSetPixels(): Int {
                return pixels.cardinality()
            }
        }

        class Flipped(private val tile: Tile) : Tile(tile.id, tile.width, tile.height) {
            override fun get(x: Int, y: Int): Boolean {
                return tile.get(width - x - 1, y)
            }

            override fun countSetPixels(): Int {
                return tile.countSetPixels()
            }
        }

        class Rotated(private val tile: Tile) : Tile(tile.id, tile.height, tile.width) {
            override fun get(x: Int, y: Int): Boolean {
                return tile.get(height - y - 1, x)
            }

            override fun countSetPixels(): Int {
                return tile.countSetPixels()
            }
        }

        abstract fun get(x: Int, y: Int): Boolean
        abstract fun countSetPixels(): Int

        fun getTransformations(): List<Tile> {
            val transformations = mutableListOf(this)

            var tile = this
            for (i in 0 until 3) {
                tile = Rotated(tile)
                transformations += tile
            }

            tile = Flipped(this)
            transformations += tile

            for (i in 0 until 3) {
                tile = Rotated(tile)
                transformations += tile
            }

            return transformations
        }

        fun rightEdgeMatches(tile: Tile): Boolean {
            for (y in 0 until height) {
                if (get(width - 1, y) != tile.get(0, y)) {
                    return false
                }
            }
            return true
        }

        fun bottomEdgeMatches(tile: Tile): Boolean {
            for (x in 0 until width) {
                if (get(x, height - 1) != tile.get(x, 0)) {
                    return false
                }
            }
            return true
        }

        fun getRoughness(): Int {
            for (transformation in getTransformations()) {
                val matches = transformation.countMatches(SEA_MONSTER)
                if (matches != 0) {
                    return countSetPixels() - matches * SEA_MONSTER.countSetPixels()
                }
            }

            throw UnsolvableException()
        }

        private fun countMatches(pattern: Tile): Int {
            var matches = 0

            for (x in 0 until (width - pattern.width)) {
                for (y in 0 until (height - pattern.height)) {
                    var match = true

                    next@for (dx in 0 until pattern.width) {
                        for (dy in 0 until pattern.height) {
                            if (pattern.get(dx, dy) && !get(x + dx, y + dy)) {
                                match = false
                                break@next
                            }
                        }
                    }

                    if (match) {
                        matches++
                    }
                }
            }

            return matches
        }

        companion object {
            private val SEA_MONSTER = parse(0, listOf(
                "..................#.",
                "#....##....##....###",
                ".#..#..#..#..#..#...",
            ))

            fun parse(id: Int, lines: List<String>): Tile {
                require(lines.isNotEmpty())

                val height = lines.size
                val width = lines.first().length

                val pixels = BitSet(width * height)
                for ((y, line) in lines.withIndex()) {
                    for ((x, char) in line.withIndex()) {
                        if (char == '#') {
                            pixels.set(y * width + x)
                        } else if (char != '.') {
                            throw IllegalArgumentException()
                        }
                    }
                }

                return Base(id, width, height, pixels)
            }
        }
    }

    private class Grid private constructor(val size: Int, private val tiles: Array<Tile?>) {
        constructor(sizeSquared: Int) : this(intSqrt(sizeSquared), arrayOfNulls(sizeSquared))

        fun get(x: Int, y: Int): Tile? {
            return tiles[x * size + y]
        }

        fun set(x: Int, y: Int, tile: Tile): Grid {
            val newTiles = tiles.copyOf()
            newTiles[x * size + y] = tile
            return Grid(size, newTiles)
        }

        fun isCompatible(x: Int, y: Int, tile: Tile): Boolean {
            if (x > 0) {
                val left = get(x - 1, y)
                if (left != null && !left.rightEdgeMatches(tile)) {
                    return false
                }
            }

            if (x < (size - 1)) {
                val right = get(x + 1, y)
                if (right != null && !tile.rightEdgeMatches(right)) {
                    return false
                }
            }

            if (y > 0) {
                val top = get(x, y - 1)
                if (top != null && !top.bottomEdgeMatches(tile)) {
                    return false
                }
            }

            if (y < (size - 1)) {
                val bottom = get(x, y + 1)
                if (bottom != null && !tile.bottomEdgeMatches(bottom)) {
                    return false
                }
            }

            return true
        }

        fun getCornerProduct(): Long {
            val tl = get(0, 0)
            val tr = get(size - 1, 0)
            val bl = get(0, size - 1)
            val br = get(size - 1, size - 1)
            check(tl != null && tr != null && bl != null && br != null)
            return tl.id.toLong() * tr.id.toLong() * bl.id.toLong() * br.id.toLong()
        }

        fun toSingleTile(): Tile {
            val first = get(0, 0)
            check(first != null && first.width == first.height)

            val totalSize = this.size * (first.width - 2)
            val pixels = BitSet(totalSize * totalSize)

            for (gridY in 0 until this.size) {
                for (gridX in 0 until this.size) {
                    val tile = get(gridX, gridY)
                    check(tile != null && tile.width == first.width && tile.height == first.height)

                    for (pixelY in 1 until (tile.height - 1)) {
                        for (pixelX in 1 until (tile.width - 1)) {
                            val y = gridY * (tile.height - 2) + (pixelY - 1)
                            val x = gridX * (tile.width - 2) + (pixelX - 1)
                            pixels.set(y * totalSize + x, tile.get(pixelX, pixelY))
                        }
                    }
                }
            }

            return Tile.Base(0, totalSize, totalSize, pixels)
        }
    }

    private val ID_REGEX = Regex("Tile (\\d+):")

    private fun intSqrt(v: Int): Int {
        val root = sqrt(v.toDouble()).toInt()
        require(root * root == v)
        return root
    }

    private fun arrange(tiles: List<Tile>): Grid {
        return arrange(Grid(tiles.size), tiles, 0, 0) ?: throw UnsolvableException()
    }

    private fun arrange(grid: Grid, remaining: List<Tile>, x: Int, y: Int): Grid? {
        if (remaining.isEmpty()) {
            return grid
        }

        val nextX: Int
        val nextY: Int
        if (x < (grid.size - 1)) {
            nextX = x + 1
            nextY = y
        } else {
            nextX = 0
            nextY = y + 1
        }

        for (tile in remaining) {
            for (transformed in tile.getTransformations()) {
                if (!grid.isCompatible(x, y, transformed)) {
                    continue
                }

                val solution = arrange(grid.set(x, y, transformed), remaining - tile, nextX, nextY)
                if (solution != null) {
                    return solution
                }
            }
        }

        return null
    }

    override fun parse(input: Sequence<String>): List<Tile> {
        val tiles = mutableListOf<Tile>()

        val it = input.iterator()
        while (it.hasNext()) {
            val match = ID_REGEX.matchEntire(it.next()) ?: throw IllegalArgumentException()
            val id = match.groupValues[1].toInt()

            val lines = mutableListOf<String>()
            while (it.hasNext()) {
                val s = it.next()
                if (s.isEmpty()) {
                    break
                }
                lines += s
            }

            tiles += Tile.parse(id, lines)
        }

        // check a square root exists
        intSqrt(tiles.size)

        return tiles
    }

    override fun solvePart1(input: List<Tile>): Long {
        return arrange(input).getCornerProduct()
    }

    override fun solvePart2(input: List<Tile>): Int {
        return arrange(input).toSingleTile().getRoughness()
    }
}
