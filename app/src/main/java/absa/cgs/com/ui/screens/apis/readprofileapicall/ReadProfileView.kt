package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.base.BaseMvpView

interface ReadProfileView : BaseMvpView {
    fun onSuccessReadProfileResponse(readProfileResponseModel: ReadProfileResponseModel)
    fun onFailureReadProfileResponse(error: String)

    fun getUserID(): String

    fun hitGetProfileCall()
}
