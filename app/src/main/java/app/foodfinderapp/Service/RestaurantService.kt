package app.foodfinderapp.Service

import app.foodfinderapp.dto.Restaurant
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantService {
    @GET("grantkollar/Foodfinder/main/json/testfile")
    fun getRestaurantFromJson(): Call<Restaurant>
}
