// This file contains the implementation of the RestaurantDao class, which provides methods to interact with
// the Firestore database for the restaurant collection. The class defines methods to add, update, delete,
// retrieve, search, and filter restaurants from the database.

package app.foodfinderapp.dao

import android.content.ContentValues.TAG
import android.util.Log
import app.foodfinderapp.dto.Restaurant
import com.google.firebase.firestore.FirebaseFirestore

class RestaurantDao {

    // Initialize an instance of the Firestore database.
    private val db = FirebaseFirestore.getInstance()

    // Method to add a restaurant to the Firestore database.
    fun addRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.restaurantID)
            .set(restaurant)
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    // Method to update a restaurant in the Firestore database.
    fun updateRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.restaurantID)
            .update(
                "name", restaurant.name,
                "address", restaurant.address,
                "category", restaurant.category,
                "contact", restaurant.contact,
                "hours", restaurant.hours
            )
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    // Method to delete a restaurant from the Firestore database.
    fun deleteRestaurant(restaurant: Restaurant) {
        db.collection("restaurants")
            .document(restaurant.restaurantID)
            .delete()
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
            }
    }

    // Method to get a restaurant by name from the Firestore database.
    fun getRestaurantByName(name: String, callback: (Restaurant?) -> Unit) {
        db.collection("restaurants")
            .document(name)
            .get()
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents: ", e)
            }
    }

    // Method to get all restaurants from the Firestore database.
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

    // Method to search for restaurants by name from the Firestore database.
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

    // Method to get all restaurants by owner ID from the Firestore database.
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
