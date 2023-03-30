package app.foodfinderapp

import BaseActivity
import android.os.Bundle
import androidx.compose.foundation.IndicationInstance
import app.foodfinderapp.databinding.ActivityMeAboutBinding

class MeAboutActivity : BaseActivity() {
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