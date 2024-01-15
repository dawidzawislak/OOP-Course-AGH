import java.util.*
import kotlin.math.max
import kotlin.math.min

class Vector2d(val x: Int, val y: Int) {

    override fun toString(): String = "($x, $y)";

    operator fun compareTo(other : Vector2d): Int {
        return if (x <= other.x && y <= other.y){
            -1
        } else if(x >= other.x && y >= other.y){
            1
        } else{
            0
        }
    }

    operator fun plus(other: Vector2d) : Vector2d = Vector2d(x + other.x, y + other.y)

    operator fun minus(other: Vector2d) : Vector2d = Vector2d(x - other.x, y - other.y)

    fun upperRight(other: Vector2d): Vector2d = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d): Vector2d = Vector2d(min(x, other.x), min(y, other.y))

    fun opposite(): Vector2d = Vector2d(-x, -y)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val otherVec2 = other as Vector2d
        return x == otherVec2.x && y == otherVec2.y
    }

    override fun hashCode(): Int = Objects.hash(x, y)
}

fun MapDirection.toUnitVector() : Vector2d = when (this) {
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.WEST -> Vector2d(-1, 0)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.EAST -> Vector2d(1, 0)
}