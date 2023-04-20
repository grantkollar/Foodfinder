package app.foodfinderapp.dto

import org.junit.Assert.*
import org.junit.Test

class MenuTest {
    private val food1 = Food(1, "Spaghetti", 10.99f, "Entrees")
    private val food2 = Food(2, "Caesar Salad", 7.99f, "Appetizers")
    private val menu = Menu(1, arrayListOf(food1, food2), "Italian")

    @Test
    fun testToString() {
        assertEquals("1 [${food1.toString()}, ${food2.toString()}] Italian", menu.toString())
    }

    @Test
    fun testAddFood() {
        val food3 = Food(3, "Tiramisu", 6.99f, "Desserts")
        menu.menuFoods.add(food3)
        assertEquals(3, menu.menuFoods.size)
        assertEquals(food3, menu.menuFoods[2])
    }

    @Test
    fun testRemoveFood() {
        menu.menuFoods.remove(food1)
        assertEquals(1, menu.menuFoods.size)
        assertEquals(food2, menu.menuFoods[0])
    }
}
