package absa.cgs.com.ui.screens.apis.updateimagesapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageRequestModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageResponseModel
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
class UpdateImageInteractor @Inject constructor() {
    private var updateImageResponseModel: UpdateImageResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessUpdateImageInteractListener(updateImageResponseModel: UpdateImageResponseModel)
        fun onRetrofitFailureUpdateImageInteractListener(error: String)
        fun onSessionExpireUpdateImageInteractListener()
        fun onErrorUpdateImageInteractListener(updateImageResponseModel: UpdateImageResponseModel)
        fun onServerExceptionUpdateImageInteractListener()
    }

    fun postUpdateImageDataToServer(updateImageRequestModel: UpdateImageRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.updateImageData(updateImageRequestModel)
                .enqueue(object : Callback<UpdateImageResponseModel> {
                    override fun onFailure(call: Call<UpdateImageResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureUpdateImageInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<UpdateImageResponseModel>, response: Response<UpdateImageResponseModel>) {


                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireUpdateImageInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                updateImageResponseModel = response.body()
                                listener.onErrorUpdateImageInteractListener(updateImageResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                updateImageResponseModel = response.body()
                                listener.onSuccessUpdateImageInteractListener(updateImageResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionUpdateImageInteractListener()
                            }
                        }
                    }

                })
    }
}