package absa.cgs.com.ui.screens.apis.readexpenseapicall

import absa.cgs.com.data.DefaultResponse
import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseRequestModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
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
class ReadExpenseInteractor @Inject constructor() {

    private var readExpenseResponseModel: ReadExpenseResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessReadExpenseInteractListener(readExpenseResponseModel: ReadExpenseResponseModel)
        fun onRetrofitFailureReadExpenseInteractListener(error: String)
        fun onSessionExpireReadExpenseInteractListener()
        fun onErrorReadExpenseInteractListener(readExpenseResponseModel: ReadExpenseResponseModel)
        fun onServerExceptionReadExpenseInteractListener()
    }


    fun readExpenseDataToServer(readExpenseRequestModel: ReadExpenseRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.readExpenseData(readExpenseRequestModel)
                .enqueue(object : Callback<ReadExpenseResponseModel> {
                    override fun onFailure(call: Call<ReadExpenseResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureReadExpenseInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<ReadExpenseResponseModel>, response: Response<ReadExpenseResponseModel>) {
                        when (response.isSuccessful) {
                            true -> {
                                when (response.code()) {
                                    HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                        listener.onSessionExpireReadExpenseInteractListener()
                                    }

                                    HttpEnum.STATUS_ERROR.code -> {
                                        readExpenseResponseModel = response.body()
                                        listener.onErrorReadExpenseInteractListener(readExpenseResponseModel!!)
                                    }

                                    HttpEnum.STATUS_OK.code -> {
                                        readExpenseResponseModel = response.body()
                                        listener.onSuccessReadExpenseInteractListener(readExpenseResponseModel!!)
                                    }
                                }
                            }
                            else -> {
                                listener.onServerExceptionReadExpenseInteractListener()
                            }

                        }
                    }

                })
    }

}
