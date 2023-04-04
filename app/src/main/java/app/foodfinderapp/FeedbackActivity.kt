package app.foodfinderapp

import BaseActivity
import android.os.Bundle
import app.foodfinderapp.databinding.ActivityFeedbackBinding

class FeedbackActivity : BaseActivity() {

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