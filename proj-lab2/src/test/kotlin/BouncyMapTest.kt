import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs

class BouncyMapTest : FunSpec ({
    test("placing animal on map on an empty cell") {
        val bouncyMap = BouncyMap(5, 5)
        val animal = Animal(Vector2d(2, 2))
        bouncyMap.place(animal) shouldBe true
    }

    test("canMoveTo should return true for valid positions") {
        val bouncyMap = BouncyMap(2, 2)
        bouncyMap.canMoveTo(Vector2d(1, 1)) shouldBe true
        bouncyMap.canMoveTo(Vector2d(0, 0)) shouldBe true
        bouncyMap.canMoveTo(Vector2d(0, 1)) shouldBe true
        bouncyMap.canMoveTo(Vector2d(1, 0)) shouldBe true
    }

    test("canMoveTo should return false for invalid positions") {
        val bouncyMap = BouncyMap(3, 3)
        bouncyMap.canMoveTo(Vector2d(4, 1)) shouldBe false
        bouncyMap.canMoveTo(Vector2d(1, 4)) shouldBe false
        bouncyMap.canMoveTo(Vector2d(-1, 1)) shouldBe false
        bouncyMap.canMoveTo(Vector2d(1, -1)) shouldBe false
    }

    test("isOccupied should return true for occupied positions") {
        val bouncyMap = BouncyMap(3, 3)
        val animal = Animal(Vector2d(1, 1))
        bouncyMap.place(animal)
        bouncyMap.isOccupied(Vector2d(1, 1)) shouldBe true
    }

    test("isOccupied should return false for unoccupied positions") {
        val bouncyMap = BouncyMap(3, 3)
        bouncyMap.isOccupied(Vector2d(1, 1)) shouldBe false
    }

    test("objectAt should return the correct animal for occupied positions") {
        val bouncyMap = BouncyMap(3, 3)
        val animal = Animal(Vector2d(1, 1))
        bouncyMap.place(animal)
        bouncyMap.objectAt(Vector2d(1, 1)) shouldNotBe null
        bouncyMap.objectAt(Vector2d(1, 1)) shouldBe animal
    }

    test("objectAt should return null for unoccupied positions") {
        val bouncyMap = BouncyMap(3, 3)
        bouncyMap.objectAt(Vector2d(1, 1)) shouldBe null
    }

    test("placing animal on other animal with empty fields on map (should be moved to other free field)") {
        val bouncyMap = BouncyMap(5, 5)
        val animal1 = Animal(Vector2d(2, 2))
        val animal2 = Animal(Vector2d(2, 2))
        bouncyMap.place(animal1) shouldBe true
        bouncyMap.place(animal2) shouldBe true

        animal2.position shouldNotBe Vector2d(2, 2)
        animal1.position shouldBe Vector2d(2, 2)
        bouncyMap.objectAt(Vector2d(2, 2)) shouldBe animal1
    }

    test("placing animal on other animal with no empty fields on map (should be placed where first animal stood)") {
        val bouncyMap = BouncyMap(1, 1)
        val animal1 = Animal(Vector2d(0, 0))
        val animal2 = Animal(Vector2d(0, 0))
        bouncyMap.place(animal1) shouldBe true
        bouncyMap.place(animal2) shouldBe true

        animal2.position shouldBe Vector2d(0, 0)
        bouncyMap.objectAt(Vector2d(0, 0)) shouldBe animal2
    }

})