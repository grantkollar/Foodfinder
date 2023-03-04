package app.foodfinderapp.login.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import app.foodfinderapp.LoginData
import okhttp3.Cookie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object UserNetwork {

    var cookie: String? = null

    private val userService = ServiceCreator.create(UserService::class.java)

    private val menuService = ServiceCreator.create(MenuService::class.java)

    suspend fun sendCode(phone: String) = userService.getCode(phone).await()
    //
    suspend fun userLogin(phone: String, code: String, cookie: String) = userService.userLogin(phone, code, cookie).await()

    //2
    suspend fun getLocation(token:String) = userService.getLocation(token).await()

    suspend fun getPasswordLogin(phone: String,password: String) = userService.getPasswordLogin(phone,password).await()



    suspend fun getGoodsList(id: String) = menuService.getGoodsList(id).await()



    suspend fun searchGoods(key: String) = menuService.searchGoods(key).await()

    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()

                    if(LoginData.SAVE_COOKIE == 1){
                        cookie = response.headers().get("Set-Cookie")?.split(";")?.get(0).toString()
                        Log.d("Cookie", cookie!!)
                        LoginData.SAVE_COOKIE = 0
                    }

                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("Response Body Is Null!")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}