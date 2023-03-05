package app.foodfinderapp.login.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.liveData
import app.foodfinderapp.login.model.EmailBackInfo
import app.foodfinderapp.login.model.User
import app.foodfinderapp.login.network.UserNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext


object Repository {

    fun sendCode(email: String) = fire(Dispatchers.IO){
        val getCode = UserNetwork.sendCode(email)

        if (getCode.status == "ok"){
            val result = getCode.status
            Result.success(result)
        }else{
            Result.failure(RuntimeException("Response status is ${getCode.status}"))
        }
    }

    fun userLogin(email: String, code: String, cookie: String)= fire(Dispatchers.IO){
        val userResponse = UserNetwork.userLogin(email, code, cookie)
        if (userResponse.code == "ok"){
            Result.success(userResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${userResponse.code}"))
        }
    }

    //3
    fun getLocation(token:String) = fire(Dispatchers.IO){
        val locationResponse = UserNetwork.getLocation(token)
        if (locationResponse.code == "ok"){
            Result.success(locationResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${locationResponse.code}"))
        }
    }

    fun getPasswordLogin(email:String, password: String) = fire(Dispatchers.IO){
        val passwordLoginResponse = UserNetwork.getPasswordLogin(email,password)
        if (passwordLoginResponse.user.status == 1 ){
            Result.success(passwordLoginResponse)
        }else{
            Result.failure(RuntimeException("Response status is ${passwordLoginResponse.user.status}"))
        }
    }




    private fun<T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            }catch (e:Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

}