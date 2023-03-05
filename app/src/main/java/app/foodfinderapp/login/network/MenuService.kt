package app.foodfinderapp.login.network

import app.foodfinderapp.login.model.MenuResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MenuService {


    @GET("menu/goodsLists")
    fun getGoodsList(@Query("id")id: String): Call<MenuResponse>


    @GET("menu/searchGoods")
    fun searchGoods(@Query("key")key: String): Call<MenuResponse>
}