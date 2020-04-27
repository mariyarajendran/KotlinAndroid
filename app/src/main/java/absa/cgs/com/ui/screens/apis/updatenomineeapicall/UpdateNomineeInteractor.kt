package absa.cgs.com.ui.screens.apis.updatenomineeapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeRequestModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateNomineeInteractor @Inject constructor() {
    private var updateNomineeResponseModel: UpdateNomineeResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessUpdateNomineeInteractListener(updateNomineeResponseModel: UpdateNomineeResponseModel)
        fun onRetrofitFailureUpdateNomineeInteractListener(error: String)
        fun onSessionExpireUpdateNomineeInteractListener()
        fun onErrorUpdateNomineeInteractListener(updateNomineeResponseModel: UpdateNomineeResponseModel)
        fun onServerExceptionUpdateNomineeInteractListener()
    }

    fun postUpdateNomineeDataToServer(updateNomineeRequestModel: UpdateNomineeRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.updateNomineeData(updateNomineeRequestModel)
                .enqueue(object : Callback<UpdateNomineeResponseModel> {
                    override fun onFailure(call: Call<UpdateNomineeResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureUpdateNomineeInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<UpdateNomineeResponseModel>, response: Response<UpdateNomineeResponseModel>) {


                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireUpdateNomineeInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                updateNomineeResponseModel = response.body()
                                listener.onErrorUpdateNomineeInteractListener(updateNomineeResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                updateNomineeResponseModel = response.body()
                                listener.onSuccessUpdateNomineeInteractListener(updateNomineeResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionUpdateNomineeInteractListener()
                            }
                        }
                    }

                })
    }
}