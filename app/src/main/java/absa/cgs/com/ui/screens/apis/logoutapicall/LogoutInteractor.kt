package absa.cgs.com.ui.screens.apis.logoutapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutInteractor @Inject constructor() {

    private var logoutResponseModel: LogoutResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessLoginInteractListener(logoutResponseModel: LogoutResponseModel)
        fun onRetrofitFailureLoginInteractListener(error: String)
        fun onSessionExpireLoginInteractListener()
        fun onErrorLoginInteractListener(logoutResponseModel: LogoutResponseModel)
        fun onServerExceptionLoginInteractListener()
    }

    fun postLogoutDataToServer(logoutRequestModel: LogoutRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.userLogout(logoutRequestModel)
                .enqueue(object : Callback<LogoutResponseModel> {
                    override fun onFailure(call: Call<LogoutResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureLoginInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<LogoutResponseModel>, response: Response<LogoutResponseModel>) {
                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireLoginInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                logoutResponseModel = response.body()
                                listener.onErrorLoginInteractListener(logoutResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                logoutResponseModel = response.body()
                                listener.onSuccessLoginInteractListener(logoutResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionLoginInteractListener()
                            }
                        }
                    }

                })
    }
}