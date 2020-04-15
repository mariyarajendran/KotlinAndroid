package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseRequestModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseResponseModel
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
class DeleteExpenseInteractor @Inject constructor() {
    private var deleteExpenseResponseModel: DeleteExpenseResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessAddExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel)
        fun onRetrofitFailureAddExpenseInteractListener(error: String)
        fun onSessionExpireAddExpenseInteractListener()
        fun onErrorAddExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel)
        fun onServerExceptionAddExpenseInteractListener()
    }

    fun postDeleteExpenseDataToServer(deleteExpenseRequestModel: DeleteExpenseRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.deleteExpenseData(deleteExpenseRequestModel)
                .enqueue(object : Callback<DeleteExpenseResponseModel> {
                    override fun onFailure(call: Call<DeleteExpenseResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureAddExpenseInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<DeleteExpenseResponseModel>, response: Response<DeleteExpenseResponseModel>) {
                        when (response.isSuccessful) {
                            true -> {
                                when (response.code()) {
                                    HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                        listener.onSessionExpireAddExpenseInteractListener()
                                    }

                                    HttpEnum.STATUS_ERROR.code -> {
                                        deleteExpenseResponseModel = response.body()
                                        listener.onErrorAddExpenseInteractListener(deleteExpenseResponseModel!!)
                                    }

                                    HttpEnum.STATUS_OK.code -> {
                                        deleteExpenseResponseModel = response.body()
                                        listener.onSuccessAddExpenseInteractListener(deleteExpenseResponseModel!!)
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