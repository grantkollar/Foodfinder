package app.foodfinderapp.login.network

import android.provider.ContactsContract.CommonDataKinds.Email
import app.foodfinderapp.LoginData
import app.foodfinderapp.login.model.EmailBackInfo
import app.foodfinderapp.login.model.LocationBackInfo
import app.foodfinderapp.login.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface UserService {


    @GET("user/getCode")
    fun getCode(@Query("email") email: String): Call<EmailBackInfo>


    @GET("user/loginEmail")
    fun userLogin(@Query("email")phone: String, @Query("code")code:String, @Header("Cookie")cookie: String): Call<UserResponse>


    @GET("address/addressLists")
    fun getLocation(@Query("token")token: String):Call<LocationBackInfo>


    @GET("user/loginPassword")
    fun getPasswordLogin(@Query("email")email: String,@Query("password")password:String):Call<PasswordLoginInfo>




    //user/testLoginNoChecking

}