package app.foodfinderapp

import BaseActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.Application.Companion.context
import app.foodfinderapp.login.network.UserNetwork
import app.foodfinderapp.ui.viewModel.UserViewModel
import app.foodfinderapp.databinding.ActivityLoginBinding
import kotlin.io.*


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by lazy {ViewModelProvider(this).get(UserViewModel::class.java)}


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflate the layout and bind its views
        setContentView(binding.root) // Set the root view of the binding object as the content view

        binding.loginPasswordDelete.setOnClickListener {
            onBackPressed()
        }

        binding.enteredPassword.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }

        //login
        binding.agreeLogin.setOnClickListener {
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()
            // if empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "please email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$".toRegex()
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "right email pls", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "login...", Toast.LENGTH_SHORT).show()

        }

        binding.convertRegister.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        //???????????????????????????????????????????????????????????????
        if(LoginData.TURN_MAIN == 1){
            onBackPressed()
        }
    }


}