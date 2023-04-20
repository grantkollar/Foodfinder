package app.foodfinderapp.dao

import android.content.ContentValues.TAG
import android.util.Log
import app.foodfinderapp.dto.Restaurant
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantDao {

    private val db = FirebaseFirestore.getInstance()

    fun addRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.name)
            .set(restaurant)
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun updateRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.name)
            .set(restaurant)
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    fun deleteRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.name)
            .delete()
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
            }
    }

    fun getRestaurantByName(name: String, callback: (Restaurant?) -> Unit) {
        db.collection("restaurants")
            .document(name)
            .get()
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

    fun getAllRestaurants(callback: (List<Restaurant>) -> Unit) {
        db.collection("restaurants")
            .get()
            .addOnSuccessListener { documents ->
                val restaurants = mutableListOf<Restaurant>()
                for (document in documents) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurants.add(restaurant)
                }
                callback(restaurants)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

    fun searchRestaurants(query: String, callback: (List<Restaurant>) -> Unit) {
        db.collection("restaurants")
            .whereGreaterThanOrEqualTo("name", query)
            .whereLessThanOrEqualTo("name", query + "\uf8ff")
            .get()
            .addOnSuccessListener { documents ->
                val restaurants = mutableListOf<Restaurant>()
                for (document in documents) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurants.add(restaurant)
                }
                callback(restaurants)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

    fun getAllRestaurantsByOwnerId(ownerId: String, callback: (List<Restaurant>) -> Unit) {
        db.collection("restaurants")
            .whereEqualTo("ownerId", ownerId)
            .get()
            .addOnSuccessListener { documents ->
                val restaurants = mutableListOf<Restaurant>()
                for (document in documents) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    restaurants.add(restaurant)
                }
                callback(restaurants)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

}
