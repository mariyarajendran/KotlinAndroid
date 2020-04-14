package absa.cgs.com.data

import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.mainbaseactivity.MainRequest
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("category/GetCategoryList")
    fun createUser(@Body body: MainRequest): Call<DefaultResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("/cablesoft/api/json/userLogin")
    fun userLogin(@Body body: LoginRequestModel): Call<LoginResponseModel>
}