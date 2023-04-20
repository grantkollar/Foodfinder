// This is a ViewModel class for managing the data of the user's owned restaurants in the FoodFinderApp.

package app.foodfinderapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.dao.RestaurantDao
import app.foodfinderapp.dto.Restaurant

class MyRestaurantViewModel : ViewModel() {

    // The ID of the user who owns the restaurants
    private val ownerId: String = FirebaseAuthDAO.getCurrentUserId()

    // The DAO object for accessing the restaurants data
    private val restaurantDao = RestaurantDao()

    // The MutableLiveData object for holding the list of restaurants
    private val _restaurantsLiveData: MutableLiveData<List<Restaurant>> = MutableLiveData()

    // The LiveData object that allows access to the list of restaurants
    val restaurantsLiveData: LiveData<List<Restaurant>> = _restaurantsLiveData

    // This method is called when a new instance of the ViewModel is created
// It loads the user's owned restaurants
    init {
        loadRestaurants()
    }

    // This private method loads the user's owned restaurants from the restaurantDao and updates the _restaurantsLiveData
    private fun loadRestaurants() {
        restaurantDao.getAllRestaurantsByOwnerId(ownerId) { restaurantList ->
            _restaurantsLiveData.postValue(restaurantList)
        }
    }

    // This method updates a restaurant in the database and reloads the user's owned restaurants
    fun updateRestaurant(restaurant: Restaurant) {
        restaurantDao.updateRestaurant(restaurant)
        loadRestaurants()
    }

    // This method deletes a restaurant from the database and reloads the user's owned restaurants
    fun deleteRestaurant(restaurant: Restaurant) {
        restaurantDao.deleteRestaurant(restaurant)
        loadRestaurants()
    }

}
