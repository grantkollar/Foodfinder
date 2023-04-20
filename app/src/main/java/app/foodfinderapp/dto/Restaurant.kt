package app.foodfinderapp.dto

import java.util.Date

data class Restaurant(
    var restaurantID: String,
    var name:String,
    var category: String,
    var hours: String,
    var contact: String,
    var address: String,
    var ownerId: String? = null){
    constructor() : this("", "", "", "", "", "")
    override fun toString(): String {
        return "$restaurantID $name $category $hours $contact"
    }
}
