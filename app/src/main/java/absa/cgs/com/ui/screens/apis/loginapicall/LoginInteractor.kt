package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.data.DefaultResponse
import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.mainbaseactivity.MainInteractor
import absa.cgs.com.ui.screens.mainbaseactivity.MainRequest
import absa.cgs.com.utils.fonts.HttpEnum
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginInteractor @Inject constructor() {

    private var LoginResponseModel: LoginResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessLoginInteractListener(loginResponseModel: LoginResponseModel)
        fun onRetrofitFailureLoginInteractListener(error: String)
        fun onSessionExpireLoginInteractListener()
        fun onErrorLoginInteractListener(loginResponseModel: LoginResponseModel)
        fun onServerExceptionLoginInteractListener()
    }


    fun postLoginDataToServer(loginRequestModel: LoginRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.userLogin(loginRequestModel)
                .enqueue(object : Callback<LoginResponseModel> {
                    override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureLoginInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                        when (response.isSuccessful) {
                            true -> {
                                when (response.code()) {
                                    HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                        listener.onSessionExpireLoginInteractListener()
                                    }

                                    HttpEnum.STATUS_ERROR.code -> {
                                        listener.onErrorLoginInteractListener(LoginResponseModel!!)
                                    }

                                    HttpEnum.STATUS_OK.code -> {
                                        LoginResponseModel = response.body()
                                        listener.onSuccessLoginInteractListener(LoginResponseModel!!)
                                    }
                                }
                            }
                            else -> {
                                listener.onServerExceptionLoginInteractListener()
                            }

                        }
                    }

                })
    }

}
