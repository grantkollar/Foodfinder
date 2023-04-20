package app.foodfinderapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    private lateinit var name : TextView
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var restBtn : Button
    private val dataBase = FirebaseFirestore.getInstance()
    private val collectionRef = dataBase.collection("restaurants")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        collectionRef.get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    val restaruantData = document.data
                }
                println("meow")
            }
            .addOnFailureListener{exception ->


            }



     /*   setContentView(R.layout.fragment_home)
        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid
        restBtn = findViewById(R.id.restaurantButtonDashboard)
        name = findViewById(R.id.dashBoardHeader)

        restBtn.setOnClickListener {
            val docRef = db.collection("restaurant").document(uid!!)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val fName = document.data!!["name"].toString()


                        name.text = fName
                    }

                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()

                }*/
       /* }*/
    }

}