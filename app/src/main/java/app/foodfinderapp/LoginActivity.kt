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
s

        binding.loginPasswordDelete.setOnClickListener {
            onBackPressed()
        }


        binding.enteredPassword.setOnClickListener {
            val intent = Intent(context, LoginPasswordActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }


        //登录
        binding.agreeLogin.setOnClickListener {
            val code = enter_code.text.toString()
            val phone = enter_phone.text.toString()
            Toast.makeText(this,UserNetwork.cookie,Toast.LENGTH_SHORT).show()
            val pers = getSharedPreferences("cookie", MODE_PRIVATE)
            Log.d("cookie test",pers.getString("getCookie","").toString())

            viewModel.resultPhoneCode(phone, code, UserNetwork.cookie.toString())
            viewModel.phoneLiveData.observe(this, Observer { result ->
                val info= result.getOrNull()
                if ("ok" == info?.code){
                    //Toast.makeText(this, "头像"+info.detailUser.user.userPhoto, Toast.LENGTH_SHORT).show()
                    // 存入到本地文件中
                    val userInfo = getSharedPreferences("userInfo", MODE_PRIVATE).edit()
                    userInfo.putString("userName",info.detailUser.user.username)
                    userInfo.putString("userPhoto",info.detailUser.user.userPhoto)
                    userInfo.putString("phone",info.detailUser.user.phone)
                    userInfo.putString("token",info.detailUser.token)
                    userInfo.apply()
                    Log.d("codeToken",info.detailUser.token)
                    LoginData.LOGIN_STATUS = 1
                    onBackPressed()
                }else{
                    Toast.makeText(this, "验证码无效！", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }

    override fun onResume() {
        super.onResume()
        //再次后退，到主页面，否则还是在验证码登录页
        if(LoginData.TURN_MAIN == 1){
            onBackPressed()
        }
    }


}