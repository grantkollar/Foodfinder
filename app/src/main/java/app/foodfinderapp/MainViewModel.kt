// This is a ViewModel class that contains the business logic for the FoodFinderApp
// It is responsible for managing the data of the app and handling communication between the app's UI and data sources.

package app.foodfinderapp

import androidx.lifecycle.*
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.dao.RestaurantDao
import app.foodfinderapp.dto.Restaurant
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import app.foodfinderapp.Service.RestaurantService

class MainViewModel : ViewModel() {

    // The MutableLiveData object for holding the list of restaurants
    private val _restaurantList = MutableLiveData<List<Restaurant>>()

    // The LiveData object that allows access to the list of restaurants
    val restaurantList: LiveData<List<Restaurant>>
        get() = _restaurantList

    // This method is called when a new instance of the ViewModel is created
// It checks the login status of the user and fetches all the restaurants
    init {
        checkLoginStatus()
        fetchAllRestaurants()
    }

    // This method checks the login status of the user
    fun checkLoginStatus() {
        return FirebaseAuthDAO.checkLoginStatus()
    }

    // This method fetches all the restaurants from the restaurantDao and updates the _restaurantList
    fun fetchAllRestaurants() {
        val restaurantDao = RestaurantDao()
        restaurantDao.getAllRestaurants { restaurantList ->
            _restaurantList.value = restaurantList
        }
    }

    // This method searches for restaurants based on the query string and updates the _restaurantList
    fun searchRestaurants(query: String) {
        RestaurantDao().searchRestaurants(query) { filteredList ->
            _restaurantList.value = filteredList
        }
    }

    // This method returns the current user
    fun getCurrentUser(): FirebaseUser? {
        val currentUser = FirebaseAuthDAO.getCurrentUser()
        return currentUser
    }

    // This method allows the UI to observe changes to the restaurantList
    fun observeRestaurantList(owner: LifecycleOwner, observer: Observer<List<Restaurant>>) {
        restaurantList.observe(owner, observer)
    }

    // This method returns a LiveData object that indicates whether the user is logged in or not
    fun isLoggedIn(): LiveData<Boolean> {
        checkLoginStatus()
        return FirebaseAuthDAO.isLoggedIn
    }

    fun addRestaurantFromJsonToCurrentUser() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestaurantService::class.java)
        val call = service.getRestaurantFromJson()

        call.enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                val restaurant = response.body()
                if (restaurant != null) {
                    val restaurantDao = RestaurantDao()
                    restaurant.ownerId = FirebaseAuthDAO.getCurrentUserId()
                    restaurantDao.addRestaurant(restaurant)
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                // Handle request failure
            }
        })
    }



}
