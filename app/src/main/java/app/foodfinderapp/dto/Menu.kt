package app.foodfinderapp.dto

import com.google.gson.annotations.SerializedName

data class Menu(var menuID : Int, var menuFoods : ArrayList<Food>,@SerializedName("manuCategories") var menuCategories : String) {
    override fun toString() : String {
        return "$menuID $menuFoods $menuCategories"
    }
}