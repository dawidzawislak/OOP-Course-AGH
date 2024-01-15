enum class MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    override fun toString(): String {
        return when (this) {
            NORTH -> "Północ"
            SOUTH -> "Południe"
            EAST -> "Wschód"
            WEST -> "Zachód"
        }
    }

    operator fun next(): MapDirection {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
        }
    }
}

