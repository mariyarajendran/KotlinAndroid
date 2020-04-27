package absa.cgs.com.ui.screens.apis.updateprofileapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface UpdateProfileView : BaseMvpView {

    fun onSuccessUpdateProfileResponse(message: String)
    fun onFailureUpdateProfileResponse(error: String)

    fun getUserId(): String
    //    fun getUserName(): String
//    fun getUserMailID(): String
    fun getUserMobileNumber(): String

    fun getUserAddress(): String
    //fun getUserProfileImg(): String
    fun getUserOfficeNumber(): String

    fun getUserOwnerName(): String
    fun getUserAgencyName(): String
    fun getUserGstNumber(): String

    fun postUpdateProfileData()

    fun navigateToMainScreen()
}