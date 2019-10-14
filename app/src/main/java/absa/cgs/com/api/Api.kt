package absa.cgs.com.api

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @Headers("Content-Type: application/json")
    @POST("category/GetCategoryList")
    fun createUser(@Body body: JSONObject): Call<DefaultResponse>
}