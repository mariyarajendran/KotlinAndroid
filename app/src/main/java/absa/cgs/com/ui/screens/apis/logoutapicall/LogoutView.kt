package absa.cgs.com.ui.screens.apis.logoutapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface LogoutView : BaseMvpView {
    fun onSuccessLogoutResponse(message: String)
    fun onFailureLogoutResponse(error: String)

    fun getUserID(): String
}