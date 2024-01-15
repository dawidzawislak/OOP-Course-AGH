class BouncyMap(private val height: Int = 0, private val width: Int = 0) : IWorldMap  {
    private val animals : HashMap<Vector2d, Animal> = HashMap()

    override fun place(animal: Animal): Boolean {
        if (animals.containsValue(animal)) {
            for ((key, value) in animals) {
                if (value == animal) {
                    animals.remove(key)
                    break
                }
            }
        }
        if (!isOccupied(animal.position) && canMoveTo(animal.position))
            animals[animal.position] = animal
        else {
            val randomFree: Vector2d? = animals.randomFreePosition(Vector2d(width,height))

            if (randomFree != null)
                animals[randomFree] = animal
            else {
                val toSwap : Vector2d? = animals.randomPosition()
                if (toSwap != null) {
                    animal.setPosition(toSwap)
                    animals[toSwap] = animal
                }
            }
        }
        return true
    }

    override fun canMoveTo(position: Vector2d): Boolean = (position < Vector2d(width, height) && position > Vector2d(0, 0))

    override fun isOccupied(position: Vector2d): Boolean = animals.containsKey(position)

    override fun objectAt(position: Vector2d): Animal? = if (isOccupied(position)) {
        animals[position]
    } else {
        null
    }

}