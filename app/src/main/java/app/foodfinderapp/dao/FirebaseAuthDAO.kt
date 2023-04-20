package app.foodfinderapp.dao

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthDAO {
    private val firebaseAuth = FirebaseAuth.getInstance()

    // login
    fun signIn(email: String, password: String, onCompleteListener: (FirebaseUser?, Exception?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onCompleteListener(firebaseAuth.currentUser, null)
                } else {
                    onCompleteListener(null, task.exception)
                }
            }
    }

    // sign up
    fun signUp(email: String, password: String, onCompleteListener: (FirebaseUser?, Exception?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onCompleteListener(firebaseAuth.currentUser, null)

                } else {
                    onCompleteListener(null, task.exception)
                }
            }
    }

    // signOut
    fun signOut() {
        firebaseAuth.signOut()
    }

    // getCurrentUser
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.uid ?: ""
    }
}
