package absa.cgs.com.ui.screens.apis.updatebankapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankRequestModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankResponseModel
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
class UpdateBankInteractor @Inject constructor() {
    private var updateBankResponseModel: UpdateBankResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessUpdateBankInteractListener(updateBankResponseModel: UpdateBankResponseModel)
        fun onRetrofitFailureUpdateBankInteractListener(error: String)
        fun onSessionExpireUpdateBankInteractListener()
        fun onErrorUpdateBankInteractListener(updateBankResponseModel: UpdateBankResponseModel)
        fun onServerExceptionUpdateBankInteractListener()
    }

    fun postUpdateBankDataToServer(updateBankRequestModel: UpdateBankRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.updateBankData(updateBankRequestModel)
                .enqueue(object : Callback<UpdateBankResponseModel> {
                    override fun onFailure(call: Call<UpdateBankResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureUpdateBankInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<UpdateBankResponseModel>, response: Response<UpdateBankResponseModel>) {


                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireUpdateBankInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                updateBankResponseModel = response.body()
                                listener.onErrorUpdateBankInteractListener(updateBankResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                updateBankResponseModel = response.body()
                                listener.onSuccessUpdateBankInteractListener(updateBankResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionUpdateBankInteractListener()
                            }
                        }
                    }

                })
    }
}