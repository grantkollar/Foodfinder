package app.foodfinderapp.dto

import android.os.Build.ID
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class User(val userPhoto:String, val ref: DatabaseReference = Firebase.database.reference.child("Authentication").child(ID))
