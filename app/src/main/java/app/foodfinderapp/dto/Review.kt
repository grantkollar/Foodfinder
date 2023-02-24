package app.foodfinderapp.dto

import android.media.Rating

class Review(var reviewID: Int, var reviewTitle: String, var reviewDescription: String, var reviewRating: Float) {
    override fun toString(): String {
        return "$reviewID $reviewTitle $reviewDescription $reviewRating"
    }
}