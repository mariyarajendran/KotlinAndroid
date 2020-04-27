package absa.cgs.com.ui.screens.apis.getnomineerelation

import absa.cgs.com.data.RetrofitClient
import absa.cgs.com.ui.screens.apis.getnomineerelation.model.NomineeRelationResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.utils.enums.HttpEnum
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNomineeRelationInteractor @Inject constructor() {

    private var nomineeRelationResponseModel: NomineeRelationResponseModel? = null


    interface OnCallHitListener {
        fun onSuccessNomineeRelationInteractListener(nomineeRelationResponseModel: NomineeRelationResponseModel)
        fun onRetrofitFailureNomineeRelationInteractListener(error: String)
        fun onSessionExpireNomineeRelationInteractListener()
        fun onErrorNomineeRelationInteractListener(nomineeRelationResponseModel: NomineeRelationResponseModel)
        fun onServerExceptionNomineeRelationInteractListener()
    }


    fun getNomineeRelationDataToServer(listener: OnCallHitListener) {
        RetrofitClient.instance.getNomineeRelations()
                .enqueue(object : Callback<NomineeRelationResponseModel> {
                    override fun onFailure(call: Call<NomineeRelationResponseModel>, t: Throwable) {
                        listener.onRetrofitFailureNomineeRelationInteractListener(t.message.toString())
                    }

                    override fun onResponse(call: Call<NomineeRelationResponseModel>, response: Response<NomineeRelationResponseModel>) {
                        when (response.code()) {
                            HttpEnum.STATUS_UNAUTHORIZED.code -> {
                                listener.onSessionExpireNomineeRelationInteractListener()
                            }

                            HttpEnum.STATUS_ERROR.code -> {
                                nomineeRelationResponseModel = response.body()
                                listener.onErrorNomineeRelationInteractListener(nomineeRelationResponseModel!!)
                            }

                            HttpEnum.STATUS_OK.code -> {
                                nomineeRelationResponseModel = response.body()
                                listener.onSuccessNomineeRelationInteractListener(nomineeRelationResponseModel!!)
                            }

                            else -> {
                                listener.onServerExceptionNomineeRelationInteractListener()
                            }
                        }

                    }

                })
    }

}
