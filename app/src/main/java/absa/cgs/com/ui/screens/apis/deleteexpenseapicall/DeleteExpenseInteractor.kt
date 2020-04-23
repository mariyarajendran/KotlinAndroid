package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseRequestModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteExpenseInteractor @Inject constructor() {
    private var deleteExpenseResponseModel: DeleteExpenseResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessDeleteExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel)
        fun onRetrofitFailureDeleteExpenseInteractListener(error: String)
        fun onSessionExpireDeleteExpenseInteractListener()
        fun onErrorDeleteExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel)
        fun onServerExceptionDeleteExpenseInteractListener()
    }

    fun postDeleteExpenseDataToServer(deleteExpenseRequestModel: DeleteExpenseRequestModel, listener: OnCallHitListener) {
        RetrofitClient.instance.deleteExpenseData(deleteExpenseRequestModel)
                .enqueue(object : Callback<DeleteExpenseResponseModel> {
                    override fun onFailure(call: Call<DeleteExpenseResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureDeleteExpenseInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<DeleteExpenseResponseModel>, response: Response<DeleteExpenseResponseModel>) {


                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireDeleteExpenseInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                deleteExpenseResponseModel = response.body()
                                listener.onErrorDeleteExpenseInteractListener(deleteExpenseResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                deleteExpenseResponseModel = response.body()
                                listener.onSuccessDeleteExpenseInteractListener(deleteExpenseResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionDeleteExpenseInteractListener()
                            }
                        }


                    }

                })
    }
}