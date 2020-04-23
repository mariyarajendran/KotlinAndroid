package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface LoginView : BaseMvpView {

    fun postLoginData()

    fun getMobileNumber(): String
    fun getPassword(): String

    fun navigateMainScreen()
}

