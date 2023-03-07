package app.foodfinderapp

import BaseActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.Application.Companion.context
import app.foodfinderapp.databinding.ActivityRegisterBinding
import app.foodfinderapp.login.network.UserNetwork
import app.foodfinderapp.ui.viewModel.UserViewModel
import kotlin.io.*

class RegisterActivity : BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater) // Inflate the layout and bind its views
        setContentView(binding.root) // Set the root view of the binding object as the content view

        binding.registerPasswordDelete.setOnClickListener {
            onBackPressed()
        }

        // Set up the click listener for the register button
        binding.agreeRegister.setOnClickListener {
            val name = binding.enteredName.text.toString()
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()

            // Validate the input
            if (name.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$".toRegex()
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            Toast.makeText(this, "register...", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }
}
