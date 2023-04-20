package app.foodfinderapp

import androidx.lifecycle.*
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.dao.RestaurantDao
import app.foodfinderapp.dto.Restaurant

class MainViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    private val _restaurantList = MutableLiveData<List<Restaurant>>()
    private val restaurantList: LiveData<List<Restaurant>>
        get() = _restaurantList

    init {
        checkLoginStatus()
        fetchAllRestaurants()
    }

    private fun checkLoginStatus() {
        val currentUser = FirebaseAuthDAO.getCurrentUser()
        _isLoggedIn.value = currentUser != null
    }

    private fun fetchAllRestaurants() {
        val restaurantDao = RestaurantDao()
        restaurantDao.getAllRestaurants { restaurantList ->
            _restaurantList.value = restaurantList
        }
    }

    fun searchRestaurants(query: String) {
        RestaurantDao().searchRestaurants(query) { filteredList ->
            _restaurantList.value = filteredList
        }
    }

    fun observeRestaurantList(owner: LifecycleOwner, observer: Observer<List<Restaurant>>) {
        restaurantList.observe(owner, observer)
    }


}
