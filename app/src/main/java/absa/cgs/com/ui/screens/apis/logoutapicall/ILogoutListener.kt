package absa.cgs.com.ui.screens.apis.logoutapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface ILogoutListener<View : LogoutView> : BaseMvpPresenter<View> {
    fun logoutApiCall()
}