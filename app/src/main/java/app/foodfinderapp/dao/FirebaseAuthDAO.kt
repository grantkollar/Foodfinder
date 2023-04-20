// This file provides methods to interact with Firebase Authentication and handle user authentication and authorization
package app.foodfinderapp.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthDAO {
    // Firebase Authentication instance
    private val firebaseAuth = FirebaseAuth.getInstance()

    // MutableLiveData to observe the user login status
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    // Login user using provided email and password
    fun signIn(
        email: String,
        password: String,
        onCompleteListener: (FirebaseUser?, Exception?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onCompleteListener(firebaseAuth.currentUser, null)
                } else {
                    onCompleteListener(null, task.exception)
                }
            }
    }

    // Sign up user using provided email and password
    fun signUp(
        email: String,
        password: String,
        onCompleteListener: (FirebaseUser?, Exception?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onCompleteListener(firebaseAuth.currentUser, null)

                } else {
                    onCompleteListener(null, task.exception)
                }
            }
    }

    // Sign out user
    fun signOut() {
        firebaseAuth.signOut()
    }

    // Get current logged in user
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Get current user's ID
    fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.uid ?: ""
    }

    // Check user login status and update MutableLiveData
    fun checkLoginStatus() {
        val currentUser = getCurrentUser()
        _isLoggedIn.value = currentUser != null
    }
}