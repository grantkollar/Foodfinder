package app.foodfinderapp.dto

import org.junit.Test
import org.junit.Assert.*

class RestaurantTest {
    @Test
    fun `Given a Restaurant object, When I call toString(), Then the expected string should be returned`() {
        val restaurant = Restaurant(
            "1234",
            "Big Burger",
            "Fast food",
            "10am-10pm",
            "123-456-7890",
            "123 Main St"
        )
        assertEquals("1234 Big Burger Fast food 10am-10pm 123-456-7890", restaurant.toString())
    }

    @Test
    fun `Given a Restaurant object with no arguments, When I call toString(), Then the expected string should be returned`() {
        val restaurant = Restaurant()
        assertEquals("  ", restaurant.toString())
    }


    @Test
    fun `Given a Restaurant object with no ownerId, When I create a new object with the same values, Then the new object should be equal to the original`() {
        val restaurant1 = Restaurant(
            "1234",
            "Big Burger",
            "Fast food",
            "10am-10pm",
            "123-456-7890",
            "123 Main St"
        )
        val restaurant2 = Restaurant(
            "1234",
            "Big Burger",
            "Fast food",
            "10am-10pm",
            "123-456-7890",
            "123 Main St"
        )
        assertEquals(restaurant1, restaurant2)
    }

    @Test
    fun `Given a Restaurant object with ownerId, When I create a new object with the same values but different ownerId, Then the new object should not be equal to the original`() {
        val restaurant1 = Restaurant(
            "1234",
            "Big Burger",
            "Fast food",
            "10am-10pm",
            "123-456-7890",
            "123 Main St",
            "abcd"
        )
        val restaurant2 = Restaurant(
            "1234",
            "Big Burger",
            "Fast food",
            "10am-10pm",
            "123-456-7890",
            "123 Main St",
            "efgh"
        )
        assertNotEquals(restaurant1, restaurant2)
    }
}
