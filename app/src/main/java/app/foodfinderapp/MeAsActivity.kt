package app.foodfinderapp

import BaseActivity
import android.os.Bundle
import app.foodfinderapp.R
import app.foodfinderapp.databinding.ActivityMeAsBinding
import app.foodfinderapp.databinding.FragmentProfileBinding

class MeAsActivity : BaseActivity() {
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