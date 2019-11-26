package absa.cgs.com.api

import absa.cgs.com.screens.MainBaseActivity.MainRequest
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("category/GetCategoryList")
    fun createUser(@Body body: MainRequest): Call<DefaultResponse>
}