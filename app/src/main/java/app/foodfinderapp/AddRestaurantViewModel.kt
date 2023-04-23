package app.foodfinderapp

import androidx.lifecycle.ViewModel
import app.foodfinderapp.dao.FirebaseAuthDAO

class AddRestaurantViewModel : ViewModel() {
    var name = ""
    var category = ""
    var hours = ""
    var contact = ""
    var address = ""

    fun getCurrentUserId(): String {
        return FirebaseAuthDAO.getCurrentUserId()
    }
}
