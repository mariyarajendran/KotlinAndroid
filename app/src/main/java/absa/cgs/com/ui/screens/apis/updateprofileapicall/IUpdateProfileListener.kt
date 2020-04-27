package absa.cgs.com.ui.screens.apis.updateprofileapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IUpdateProfileListener<View : UpdateProfileView> : BaseMvpPresenter<View> {
    fun postUpdateProfileApiCall()
    fun validateProfileDatas()
}