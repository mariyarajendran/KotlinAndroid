package absa.cgs.com.ui.screens.apis.loginapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter


interface ILoginListener<View : LoginView> : BaseMvpPresenter<View> {

    fun postLoginData()

    fun validateLoginData()

}
