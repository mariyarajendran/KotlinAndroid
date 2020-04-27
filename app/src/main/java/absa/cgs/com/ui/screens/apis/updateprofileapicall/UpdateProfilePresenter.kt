package absa.cgs.com.ui.screens.apis.updateprofileapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateProfilePresenter<View : UpdateProfileView> @Inject constructor(var updateProfileInteractor: UpdateProfileInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateProfileListener<View>, UpdateProfileInteractor.OnCallHitListener {

    override fun postUpdateProfileApiCall() {
        var updateProfileRequestModel = UpdateProfileRequestModel(getBaseMvpVieww().getUserId(), getBaseMvpVieww().getUserMobileNumber(), getBaseMvpVieww().getUserAddress(), getBaseMvpVieww().getUserOfficeNumber(), getBaseMvpVieww().getUserOwnerName(), getBaseMvpVieww().getUserAgencyName(), getBaseMvpVieww().getUserGstNumber())
        updateProfileInteractor.postUpdateExpenseDataToServer(updateProfileRequestModel, this)
    }

    override fun onSuccessUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().navigateToMainScreen()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(updateProfileResponseModel.message))
    }

    override fun onRetrofitFailureUpdateProfileInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireUpdateProfileInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(updateProfileResponseModel.message)
    }

    override fun onServerExceptionUpdateProfileInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateProfileDatas() {
        if (getBaseMvpVieww().getUserMobileNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileMobileNullString))
        } else if (getBaseMvpVieww().getUserOfficeNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileofficeContactNullString))
        } else if (getBaseMvpVieww().getUserOwnerName().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileOwnerNameNullString))
        } else if (getBaseMvpVieww().getUserAgencyName().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileAgencyNameNullString))
        } else if (getBaseMvpVieww().getUserAddress().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileAddressNullString))
        } else if (getBaseMvpVieww().getUserGstNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateProfileGstNumberNullString))
        } else {
            getBaseMvpVieww().postUpdateProfileData()
        }
    }


}