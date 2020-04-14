package absa.cgs.com.ui.screens.apis.logoutapicall

import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class LogoutPresenter<View : LogoutView> @Inject constructor(var logoutInteractor: LogoutInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), ILogoutListener<View>, LogoutInteractor.OnCallHitListener {
    override fun logoutApiCall() {
        var logoutRequestModel = LogoutRequestModel(getBaseMvpVieww().getUserID())
        logoutInteractor.postLogoutDataToServer(logoutRequestModel, this)

    }

    override fun onSuccessLoginInteractListener(logoutResponseModel: LogoutResponseModel) {
        commonUtils.showToastLong(logoutResponseModel.message)

    }

    override fun onRetrofitFailureLoginInteractListener(error: String) {

    }

    override fun onSessionExpireLoginInteractListener() {

    }

    override fun onErrorLoginInteractListener(logoutResponseModel: LogoutResponseModel) {

    }

    override fun onServerExceptionLoginInteractListener() {

    }


}