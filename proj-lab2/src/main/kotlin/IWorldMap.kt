interface IWorldMap {
    fun place(animal: Animal): Boolean

    fun canMoveTo(position: Vector2d): Boolean

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Any?
}