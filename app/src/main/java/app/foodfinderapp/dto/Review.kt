package app.foodfinderapp.dto

import android.media.Rating

data class Review(
    val reviewID: Int,
    var reviewTitle: String,
    var reviewDescription: String,
    var reviewRating: Float) {
    override fun toString(): String {
        return "$reviewID $reviewTitle $reviewDescription $reviewRating"
    }
}