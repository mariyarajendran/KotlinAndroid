package absa.cgs.com.ui.screens.apis.updatenomineeapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IUpdateNomineeListener<View : UpdateNomineeView> : BaseMvpPresenter<View> {
    fun postUpdateNomineeApiCall()
    fun validateNomineeDatas()
}