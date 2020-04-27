package absa.cgs.com.ui.screens.apis.updatenomineeapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface UpdateNomineeView : BaseMvpView {

    fun getUserId(): String
    fun getNomineeName():String
    fun getNomineePhoneNumber(): String
    fun getNomineeAddress(): String
    fun getNomineeRelations(): String
    fun navigateToMainScreen()
    fun postNomineeData()
}