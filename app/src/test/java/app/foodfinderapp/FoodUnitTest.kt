package app.foodfinderapp.dto

import org.junit.Assert.*
import org.junit.Test

class FoodTest {

    @Test
    fun testToString() {
        val food = Food(1, "Pizza", 9.99f, "Entrees")
        val expectedString = "1 Pizza 9.99 Entrees"
        assertEquals(expectedString, food.toString())
    }

    @Test
    fun testFoodProperties() {
        val food = Food(1, "Burger", 8.99f, "Entrees")
        assertEquals(1, food.foodID)
        assertEquals("Burger", food.foodName)
        assertEquals(8.99f, food.foodPrice, 0.01f)
        assertEquals("Entrees", food.foodCategory)
    }
}
