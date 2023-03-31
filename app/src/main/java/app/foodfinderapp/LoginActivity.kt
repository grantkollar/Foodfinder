package app.foodfinderapp

import BaseActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.Application.Companion.context
import app.foodfinderapp.ui.viewModel.UserViewModel
import app.foodfinderapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
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


        val prefs = applicationContext.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            binding.enteredEmail.setText(prefs.getString("email", ""))
            binding.enteredPassword.setText(prefs.getString("password", ""))
            binding.rememberPasswordCheckbox.isChecked = true
        }


        binding.agreeLogin.setOnClickListener {
            val email = binding.enteredEmail.text.toString()
            val password = binding.enteredPassword.text.toString()

            // if empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "please email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val emailRegex = "^([a-zA-Z0-9._-])+@([a-zA-Z0-9._-])+$".toRegex()
            if (!emailRegex.matches(email)) {
                Toast.makeText(this, "right email pls", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        //save
                        saveLoginInfo()
                        // to main
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        Toast.makeText(this, "Logged in as ${FirebaseAuth.getInstance().currentUser?.email}", Toast.LENGTH_SHORT).show()
                    } else {

                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.convertRegister.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        //再次后退，到主页面，否则还是在验证码登录页
        if(LoginData.TURN_MAIN == 1){
            onBackPressed()
        }
    }

    private fun saveLoginInfo() {
        val prefs = applicationContext.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        if (binding.rememberPasswordCheckbox.isChecked) {
            editor.putString("email", binding.enteredEmail.text.toString())
            editor.putString("password", binding.enteredPassword.text.toString())
            editor.putBoolean("remember_password", true)
        } else {
            editor.clear()
        }
        editor.apply()
    }


}