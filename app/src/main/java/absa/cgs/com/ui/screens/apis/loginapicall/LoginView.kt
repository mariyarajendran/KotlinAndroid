package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface LoginView : BaseMvpView {
    fun onSuccessLoginResponse(message: String)
    fun onFailureLoginResponse(error: String)

    fun getMobileNumber(): String
    fun getPassword(): String
}
