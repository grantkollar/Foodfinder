package app.foodfinderapp.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Restaurant(val restaurantID : Int, @SerializedName("name") var name:String, @SerializedName("category") var category : String,@SerializedName("hours") var hours: String,@SerializedName("contact") var contact: String){
    override fun toString(): String {
        return "$restaurantID $name $category $hours $contact"
    }
}