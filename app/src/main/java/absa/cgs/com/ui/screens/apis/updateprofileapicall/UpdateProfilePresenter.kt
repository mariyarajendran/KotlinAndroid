package absa.cgs.com.ui.screens.apis.updateprofileapicall

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateProfilePresenter<View : UpdateProfileView> @Inject constructor(var updateProfileInteractor: UpdateProfileInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateProfileListener<View>, UpdateProfileInteractor.OnCallHitListener {
    override fun postUpdateProfileApiCall() {
        var updateProfileRequestModel = UpdateProfileRequestModel(getBaseMvpVieww().getUserId(), getBaseMvpVieww().getUserName(), getBaseMvpVieww().getUserMailID(), getBaseMvpVieww().getUserMobileNumber(), getBaseMvpVieww().getUserAddress(), getBaseMvpVieww().getUserProfileImg(), getBaseMvpVieww().getUserOfficeNumber(), getBaseMvpVieww().getUserOwnerName(), getBaseMvpVieww().getUserAgencyName(), getBaseMvpVieww().getUserGstNumber())
        updateProfileInteractor.postUpdateExpenseDataToServer(updateProfileRequestModel, this)
    }

    override fun onSuccessUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel) {
        commonUtils.showToastLong(updateProfileResponseModel.message)
    }

    override fun onRetrofitFailureUpdateProfileInteractListener(error: String) {

    }

    override fun onSessionExpireUpdateProfileInteractListener() {

    }

    override fun onErrorUpdateProfileInteractListener(updateProfileResponseModel: UpdateProfileResponseModel) {

    }

    override fun onServerExceptionUpdateProfileInteractListener(status: Int) {

    }


}