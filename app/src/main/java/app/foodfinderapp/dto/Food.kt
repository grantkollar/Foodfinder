package app.foodfinderapp.dto

data class Food(var foodID:Int, var foodName: String, var foodPrice: Float, var foodCategory: String) {
    override fun toString(): String {
        return "$foodID $foodName $foodPrice $foodCategory"
    }
}
