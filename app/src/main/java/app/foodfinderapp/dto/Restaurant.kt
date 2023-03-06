package app.foodfinderapp.dto

import java.util.Date

data class Restaurant(val restaurantID : Int, var name:String,var category : String, var hours: String, var contact: String){
    override fun toString(): String {
        return "$restaurantID $name $category $hours $contact"
    }
}