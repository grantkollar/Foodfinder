package app.foodfinderapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.foodfinderapp.R
import app.foodfinderapp.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_feedback)
        binding.toolbarOpinion.setOnClickListener { onBackPressed() }
    }
}