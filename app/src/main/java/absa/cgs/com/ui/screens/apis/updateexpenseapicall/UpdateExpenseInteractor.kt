package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateExpenseInteractor @Inject constructor() {
    private var updateExpenseResponseModel: UpdateExpenseResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel)
        fun onRetrofitFailureUpdateExpenseInteractListener(error: String)
        fun onSessionExpireUpdateExpenseInteractListener()
        fun onErrorUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel)
        fun onServerExceptionUpdateExpenseInteractListener()
    }

    fun postUpdateExpenseDataToServer(udateExpenseRequestModel: UpdateExpenseRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.updateExpenseData(udateExpenseRequestModel)
                .enqueue(object : Callback<UpdateExpenseResponseModel> {
                    override fun onFailure(call: Call<UpdateExpenseResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureUpdateExpenseInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<UpdateExpenseResponseModel>, response: Response<UpdateExpenseResponseModel>) {


                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireUpdateExpenseInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                updateExpenseResponseModel = response.body()
                                listener.onErrorUpdateExpenseInteractListener(updateExpenseResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                updateExpenseResponseModel = response.body()
                                listener.onSuccessUpdateExpenseInteractListener(updateExpenseResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionUpdateExpenseInteractListener()
                            }
                        }


                    }

                })
    }
}