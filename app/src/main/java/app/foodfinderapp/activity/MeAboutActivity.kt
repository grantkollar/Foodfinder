package app.foodfinderapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.R
import app.foodfinderapp.databinding.ActivityMeAboutBinding

class MeAboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_me_about)
        binding.toolbarAbout.setOnClickListener{ onBackPressed()}

    }
}