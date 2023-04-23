package app.foodfinderapp.dto

import android.media.Rating

data class Review(
    var reviewID: Int,
    var reviewTitle: String,
    var reviewDescription: String,
    var reviewRating: Float,
    var reviewImageURL: String?,
    val restaurantID: String
) {
    override fun toString(): String {
        return "$reviewID $reviewTitle $reviewDescription $reviewRating"
    }
}