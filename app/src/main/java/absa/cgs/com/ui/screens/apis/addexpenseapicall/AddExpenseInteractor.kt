package absa.cgs.com.ui.screens.apis.addexpenseapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.logoutapicall.LogoutInteractor
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.utils.fonts.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddExpenseInteractor @Inject constructor() {
    private var addExpenseResponseModel: AddExpenseResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel)
        fun onRetrofitFailureAddExpenseInteractListener(error: String)
        fun onSessionExpireAddExpenseInteractListener()
        fun onErrorAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel)
        fun onServerExceptionAddExpenseInteractListener()
    }

    fun postAddExpenseDataToServer(addExpenseRequestModel: AddExpenseRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.addExpenseData(addExpenseRequestModel)
                .enqueue(object : Callback<AddExpenseResponseModel> {
                    override fun onFailure(call: Call<AddExpenseResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureAddExpenseInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<AddExpenseResponseModel>, response: Response<AddExpenseResponseModel>) {
                        when (response.isSuccessful) {
                            true -> {
                                when (response.code()) {
                                    HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                        listener.onSessionExpireAddExpenseInteractListener()
                                    }

                                    HttpEnum.STATUS_ERROR.code -> {
                                        addExpenseResponseModel = response.body()
                                        listener.onErrorAddExpenseInteractListener(addExpenseResponseModel!!)
                                    }

                                    HttpEnum.STATUS_OK.code -> {
                                        addExpenseResponseModel = response.body()
                                        listener.onSuccessAddExpenseInteractListener(addExpenseResponseModel!!)
                                    }
                                }
                            }
                            else -> {
                                listener.onServerExceptionAddExpenseInteractListener()
                            }

                        }
                    }

                })
    }
}