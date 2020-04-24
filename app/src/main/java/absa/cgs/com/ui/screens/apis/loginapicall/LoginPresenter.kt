package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.SessionUtils
import javax.inject.Inject


class LoginPresenter<View : LoginView> @Inject constructor(val loginInteractor: LoginInteractor, val sessionUtils: SessionUtils, val commonUtils: CommonUtils) : BasePresenter<View>(), ILoginListener<View>, LoginInteractor.OnCallHitListener {


    override fun postLoginData() {
        val loginRequestModel = LoginRequestModel(getBaseMvpVieww().getMobileNumber(), getBaseMvpVieww().getPassword())
        loginInteractor.postLoginDataToServer(loginRequestModel, this)
    }

    override fun onSuccessLoginInteractListener(loginResponseModel: LoginResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        sessionUtils.loginSessionData = commonUtils.cutNull(commonUtils.putLoginSessionData(loginResponseModel))
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(loginResponseModel.message))
        getBaseMvpVieww().navigateMainScreen()
        getBaseMvpVieww().getAuthTokenFromSession()
        getBaseMvpVieww().getUserIdFronSession()
    }


    override fun onRetrofitFailureLoginInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireLoginInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorLoginInteractListener(loginResponseModel: LoginResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(loginResponseModel.message)
    }

    override fun onServerExceptionLoginInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateLoginData() {
        if (getBaseMvpVieww().getMobileNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AuthLoginMobileNullString))
        } else if (getBaseMvpVieww().getPassword().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AuthLoginPasswordString))
        } else {
            getBaseMvpVieww().postLoginData()
        }
    }


}
