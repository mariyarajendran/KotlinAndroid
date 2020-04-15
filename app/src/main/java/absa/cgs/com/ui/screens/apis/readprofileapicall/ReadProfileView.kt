package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface ReadProfileView : BaseMvpView {
    fun onSuccessReadProfileResponse(message: String)
    fun onFailureReadProfileResponse(error: String)

    fun getUserID(): String
}
