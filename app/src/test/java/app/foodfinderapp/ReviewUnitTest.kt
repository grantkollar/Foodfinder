package app.foodfinderapp.dto

import org.junit.Assert.assertEquals
import org.junit.Test

class ReviewTest {

    private val review = Review(
        1,
        "Delicious Food",
        "The food was amazing! I loved every bite.",
        4.5f
    )

    @Test
    fun testToString() {
        val expectedString = "1 Delicious Food The food was amazing! I loved every bite. 4.5"
        assertEquals(expectedString, review.toString())
    }

    @Test
    fun testReviewID() {
        assertEquals(1, review.reviewID)
    }

    @Test
    fun testReviewTitle() {
        assertEquals("Delicious Food", review.reviewTitle)
    }

    @Test
    fun testReviewDescription() {
        assertEquals("The food was amazing! I loved every bite.", review.reviewDescription)
    }

    @Test
    fun testReviewRating() {
        assertEquals(4.5f, review.reviewRating, 0.0f)
    }
}
