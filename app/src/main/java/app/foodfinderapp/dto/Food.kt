package app.foodfinderapp.dto

/**
 * Represents a food item.
 * @param foodID The unique identifier for the food.
 * @param foodName The name of the food.
 * @param foodPrice The price of the food.
 * @param foodCategory The category of the food.
 */
class Food(var foodID: Int, var foodName: String, var foodPrice: Float, var foodCategory: String) {
    override fun toString(): String {
        return "$foodID $foodName $foodPrice $foodCategory"
    }
}
