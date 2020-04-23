package absa.cgs.com.ui.screens.apis.logoutapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutRequestModel
import absa.cgs.com.ui.screens.apis.logoutapicall.model.LogoutResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.SessionUtils
import javax.inject.Inject

class LogoutPresenter<View : LogoutView> @Inject constructor(var logoutInteractor: LogoutInteractor, private val sessionUtils: SessionUtils) : BasePresenter<View>(), ILogoutListener<View>, LogoutInteractor.OnCallHitListener {
    override fun logoutApiCall() {
        var logoutRequestModel = LogoutRequestModel(getBaseMvpVieww().getUserID())
        logoutInteractor.postLogoutDataToServer(logoutRequestModel, this)

    }

    override fun onSuccessLoginInteractListener(logoutResponseModel: LogoutResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        sessionUtils.deleteLoginSession()
        getBaseMvpVieww().showToastLong(logoutResponseModel.message)
        getBaseMvpVieww().naviageToLoginScreen()

    }

    override fun onRetrofitFailureLoginInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireLoginInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorLoginInteractListener(logoutResponseModel: LogoutResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(logoutResponseModel.message)
    }

    override fun onServerExceptionLoginInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}