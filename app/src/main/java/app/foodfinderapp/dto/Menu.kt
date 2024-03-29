package app.foodfinderapp.dto

data class Menu(var menuID : Int,
                var menuFoods : ArrayList<Food>,
                var menuCategories : String) {
    override fun toString() : String {
        return "$menuID $menuFoods $menuCategories"
    }
}