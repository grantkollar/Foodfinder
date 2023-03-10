package app.foodfinderapp.dto

import android.media.Rating
import com.google.gson.annotations.SerializedName

data class Review(var reviewID: Int, @SerializedName("reviewTitle") var reviewTitle: String,@SerializedName("reviewDescription") var reviewDescription: String, var reviewRating: Float) {
    override fun toString(): String {
        return "$reviewID $reviewTitle $reviewDescription $reviewRating"
    }
}