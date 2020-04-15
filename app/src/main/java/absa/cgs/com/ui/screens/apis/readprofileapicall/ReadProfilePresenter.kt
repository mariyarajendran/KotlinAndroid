package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class ReadProfilePresenter<View : ReadProfileView> @Inject constructor(var readProfileInteractor: ReadProfileInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IReadProfileListener<View>, ReadProfileInteractor.OnCallHitListener {
    override fun readUserProfileData() {
        val readProfileRequestModel = ReadProfileRequestModel(getBaseMvpVieww().getUserID())
        readProfileInteractor.postLoginDataToServer(readProfileRequestModel, this)
    }

    override fun onSuccessReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel) {
        commonUtils.showToastLong(readProfileResponseModel.user_details.user_gst_number)
    }

    override fun onRetrofitFailureReadProfileInteractListener(error: String) {

    }

    override fun onSessionExpireReadProfileInteractListener() {

    }

    override fun onErrorReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel) {

    }

    override fun onServerExceptionReadProfileInteractListener() {

    }


}
