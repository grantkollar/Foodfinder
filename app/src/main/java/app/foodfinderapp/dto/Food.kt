package app.foodfinderapp.dto

/* This data class represents a Food object in the Food Finder App. It contains the following properties:

foodID: an integer representing the unique ID of the food
foodName: a string representing the name of the food
foodPrice: a float representing the price of the food
foodCategory: a string representing the category of the food, e.g. appetizers, entrees, desserts, etc.

overrides the toString() method to provide a formatted string representation of the Food object. */

data class Food(var foodID:Int,
                var foodName: String,
                var foodPrice: Float,
                var foodCategory: String) {
    override fun toString(): String {
        return "$foodID $foodName $foodPrice $foodCategory"
    }
}
