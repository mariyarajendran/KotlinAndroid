package absa.cgs.com.ui.screens.register

import absa.cgs.com.ui.screens.base.BaseMvpView

interface RegistrationView : BaseMvpView {

    fun onSuccessResponse(message: String)
    fun onFailureResponse(error: String)

    fun showDialogMaleOrFemale()
}