package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class LoginPresenter<View : LoginView> @Inject constructor(var loginInteractor: LoginInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), ILoginListener<View>, LoginInteractor.OnCallHitListener {


    override fun postLoginData() {
        val loginRequestModel = LoginRequestModel(getBaseMvpVieww().getMobileNumber(), getBaseMvpVieww().getPassword())
        loginInteractor.postLoginDataToServer(loginRequestModel, this)
    }

    override fun onSuccessLoginInteractListener(loginResponseModel: LoginResponseModel) {
        commonUtils.showToastLong(loginResponseModel.user_details.user_access_token)
    }


    override fun onRetrofitFailureLoginInteractListener(error: String) {
        commonUtils.showToastLong(error)
    }

    override fun onSessionExpireLoginInteractListener() {

    }

    override fun onErrorLoginInteractListener(loginResponseModel: LoginResponseModel) {

    }

    override fun onServerExceptionLoginInteractListener() {
        //commonUtils.showToastLong("Exxor server")
    }


}
