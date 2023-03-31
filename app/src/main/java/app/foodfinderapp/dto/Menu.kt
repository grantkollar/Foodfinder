package app.foodfinderapp.dto

data class Menu(
    val menuID : Int,
    var menuFoods : ArrayList<Food>,
    var menuCategories : String
    ) {
    override fun toString() : String {
        return "$menuID $menuFoods $menuCategories"
    }
}