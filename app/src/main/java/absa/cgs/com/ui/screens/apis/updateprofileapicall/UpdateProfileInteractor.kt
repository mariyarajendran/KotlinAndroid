package absa.cgs.com.ui.screens.apis.updateprofileapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateProfileInteractor @Inject constructor() {
    private var updateProfileResponseModel: UpdateProfileResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel)
        fun onRetrofitFailureUpdateProfileInteractListener(error: String)
        fun onSessionExpireUpdateProfileInteractListener()
        fun onErrorUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel)
        fun onServerExceptionUpdateProfileInteractListener(status: Int)
    }

    fun postUpdateExpenseDataToServer(updateProfileRequestModel: UpdateProfileRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.updateProfileData(updateProfileRequestModel)
                .enqueue(object : Callback<UpdateProfileResponseModel> {
                    override fun onFailure(call: Call<UpdateProfileResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureUpdateProfileInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<UpdateProfileResponseModel>, response: Response<UpdateProfileResponseModel>) {
                        when (response.isSuccessful) {
                            true -> {
                                when (response.code()) {
                                    HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                        listener.onSessionExpireUpdateProfileInteractListener()
                                    }

                                    HttpEnum.STATUS_ERROR.code -> {
                                        updateProfileResponseModel = response.body()
                                        listener.onErrorUpdateProfileInteractListener(updateProfileResponseModel!!)
                                    }

                                    HttpEnum.STATUS_OK.code -> {
                                        updateProfileResponseModel = response.body()
                                        listener.onSuccessUpdateProfileInteractListener(updateProfileResponseModel!!)
                                    }
                                }
                            }
                            else -> {
                                listener.onServerExceptionUpdateProfileInteractListener(response.code())
                            }

                        }
                    }

                })
    }
}