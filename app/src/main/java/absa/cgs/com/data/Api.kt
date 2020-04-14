package absa.cgs.com.data

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.mainbaseactivity.MainRequest
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @POST("category/GetCategoryList")
    fun createUser(@Body body: MainRequest): Call<DefaultResponse>

    @POST("/cablesoft/api/json/userLogin")
    fun userLogin(@Body body: LoginRequestModel): Call<LoginResponseModel>

    @POST("/cablesoft/api/json/logout")
    fun userLogout(@Body body: LogoutRequestModel): Call<LogoutResponseModel>

    @POST("/cablesoft/api/json/addNewExpense")
    fun addExpenseData(@Body body: AddExpenseRequestModel): Call<AddExpenseResponseModel>

    @POST("/cablesoft/api/json/updateExpense")
    fun updateExpenseData(@Body body: UpdateExpenseRequestModel): Call<UpdateExpenseResponseModel>
}