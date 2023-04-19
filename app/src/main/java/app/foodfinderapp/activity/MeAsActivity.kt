package app.foodfinderapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.R
import app.foodfinderapp.databinding.ActivityMeAsBinding

class MeAsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMeAsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeAsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_me_as)
        binding.toolbar.setOnClickListener{onBackPressed()}

    }
}