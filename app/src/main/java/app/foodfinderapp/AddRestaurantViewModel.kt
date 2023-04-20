package app.foodfinderapp

import androidx.lifecycle.ViewModel
import app.foodfinderapp.dao.FirebaseAuthDAO
import java.util.*

class AddRestaurantViewModel : ViewModel() {
    var name = ""
    var category = ""
    var hours = ""
    var contact = ""
    var address = ""
    var restaurantID = UUID.randomUUID().toString() //generate random id

    fun getCurrentUserId(): String {
        return FirebaseAuthDAO.getCurrentUserId()
    }
}
