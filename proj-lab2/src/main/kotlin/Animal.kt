class Animal(var position: Vector2d, private var orientation: MapDirection = MapDirection.NORTH) {
    override fun toString(): String = when (orientation) {
        MapDirection.NORTH -> "^"
        MapDirection.SOUTH -> "v"
        MapDirection.EAST -> ">"
        MapDirection.WEST -> "<"
    }

    fun isAt(position: Vector2d?): Boolean = this.position == position

    fun move(direction: MoveDirection) {
        var newPosition = position
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> newPosition = position + orientation.toUnitVector()
            MoveDirection.BACKWARD -> newPosition = position + orientation.toUnitVector().opposite()
        }

        if (newPosition !== position && (newPosition.x in 0..4 && newPosition.y in 0..4))  position = newPosition
    }
}