package app.foodfinderapp.util

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView()
    }
}