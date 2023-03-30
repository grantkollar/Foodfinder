package app.foodfinderapp

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import app.foodfinderapp.dto.Restaurant

/**
 * RestaurantService is responsible for fetching restaurant data from Firestore.
 */
class RestaurantService {
    private val db = Firebase.firestore

    /**
     * Fetches all restaurants from Firestore.
     *
     * @param onSuccess is called when the data is fetched successfully, which takes a list of restaurants as a parameter.
     * @param onFailure is called when there's an error fetching data, which takes an exception as a parameter.
     */

    fun getAllRestaurants(
        onSuccess: (List<Restaurant>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val restaurantRef = db.collection("restaurants")
        restaurantRef.get()
            .addOnSuccessListener { documents ->
                val restaurantList = documents.map { it.toObject(Restaurant::class.java) }
                onSuccess(restaurantList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}