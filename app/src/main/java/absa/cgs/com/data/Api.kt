package absa.cgs.com.data

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseRequestModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseResponseModel
import absa.cgs.com.ui.screens.apis.getnomineerelation.model.NomineeRelationResponseModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseRequestModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankRequestModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageRequestModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageResponseModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeRequestModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
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

    @POST("/cablesoft/api/json/deleteExpense")
    fun deleteExpenseData(@Body body: DeleteExpenseRequestModel): Call<DeleteExpenseResponseModel>

    @POST("/cablesoft/api/json/updateUserDetails")
    fun updateProfileData(@Body body: UpdateProfileRequestModel): Call<UpdateProfileResponseModel>

    @POST("/cablesoft/api/json/getAllUserDetails")
    fun readProfileData(@Body body: ReadProfileRequestModel): Call<ReadProfileResponseModel>

    @POST("/cablesoft/api/json/getExpenseDetails")
    fun readExpenseData(@Body body: ReadExpenseRequestModel): Call<ReadExpenseResponseModel>

    @POST("/cablesoft/api/json/updateNomineeDetails")
    fun updateNomineeData(@Body body: UpdateNomineeRequestModel): Call<UpdateNomineeResponseModel>

    @POST("/cablesoft/api/json/updateBankDetails")
    fun updateBankData(@Body body: UpdateBankRequestModel): Call<UpdateBankResponseModel>

    @POST("/cablesoft/api/json/uploadImageCall")
    fun updateImageData(@Body body: UpdateImageRequestModel): Call<UpdateImageResponseModel>

    @GET("/cablesoft/api/json/getNomineeRelations")
    fun getNomineeRelations(): Call<NomineeRelationResponseModel>

}