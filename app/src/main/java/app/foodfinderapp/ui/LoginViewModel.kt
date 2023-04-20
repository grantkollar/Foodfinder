package app.foodfinderapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.foodfinderapp.LoginData
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.databinding.ActivityLoginBinding

class LoginViewModel : ViewModel() {

    private lateinit var binding: ActivityLoginBinding
    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage


    fun setBinding(binding: ActivityLoginBinding) {
        this.binding = binding
        this.context = binding.root.context
        sharedPreferences = context.getSharedPreferences("login_info", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun loadLoginInfoFromPreferences(binding: ActivityLoginBinding) {
        val isRemember = sharedPreferences.getBoolean("remember_password", false)
        if (isRemember) {
            this.binding.enteredEmail.setText(sharedPreferences.getString("email", ""))
            this.binding.enteredPassword.setText(sharedPreferences.getString("password", ""))
            this.binding.rememberPasswordCheckbox.isChecked = true
        }
    }

    fun login(email: String, password: String, rememberPassword: Boolean) {

        if (email.isEmpty() || password.isEmpty()) {
            _toastMessage.value = "Please enter both email and password."
            return
        }

        val emailRegex = "^([a-zA-Z0-9._-])+@([a-zA-Z0-9._-])+$".toRegex()
        if (!emailRegex.matches(email)) {
            _toastMessage.value = "Invalid email format."
            return
        }

        FirebaseAuthDAO.signIn(email, password) { firebaseUser, exception ->
            if (firebaseUser != null) {
                // Save login information to preferences if "remember me" is checked
                if (rememberPassword) {
                    saveLoginInfo(email, password)
                } else {
                    clearLoginInfo()
                }
                // Change login status and go to main activity
                LoginData.LOGIN_STATUS = 1
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)

            } else {
                Toast.makeText(context, "Login failed: ${exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLoginInfo(email: String, password: String) {
        editor.putBoolean("remember_password", true)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun clearLoginInfo() {
        editor.clear().apply()
    }

}