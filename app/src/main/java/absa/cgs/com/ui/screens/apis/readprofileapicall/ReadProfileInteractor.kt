package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadProfileInteractor @Inject constructor() {

    private var readProfileResponseModel: ReadProfileResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel)
        fun onRetrofitFailureReadProfileInteractListener(error: String)
        fun onSessionExpireReadProfileInteractListener()
        fun onErrorReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel)
        fun onServerExceptionReadProfileInteractListener()
    }


    fun getReadprofileDataToServer(readProfileRequestModel: ReadProfileRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.readProfileData(readProfileRequestModel)
                .enqueue(object : Callback<ReadProfileResponseModel> {
                    override fun onFailure(call: Call<ReadProfileResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureReadProfileInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<ReadProfileResponseModel>, response: Response<ReadProfileResponseModel>) {
                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireReadProfileInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                readProfileResponseModel = response.body()
                                listener.onErrorReadProfileInteractListener(readProfileResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                readProfileResponseModel = response.body()
                                listener.onSuccessReadProfileInteractListener(readProfileResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionReadProfileInteractListener()
                            }
                        }

                    }

                })
    }

}
