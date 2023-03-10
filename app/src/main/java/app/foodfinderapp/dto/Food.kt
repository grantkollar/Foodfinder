package app.foodfinderapp.dto

import com.google.gson.annotations.SerializedName

data class Food(var foodID:Int, @SerializedName("foodName")var foodName: String, var foodPrice: Float,@SerializedName("foodCategory") var foodCategory: String) {
    override fun toString(): String {
        return "$foodID $foodName $foodPrice $foodCategory"
    }
}
