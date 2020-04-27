package absa.cgs.com.ui.screens.apis.updatenomineeapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeRequestModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateNomineePresenter<View : UpdateNomineeView> @Inject constructor(var updateNomineeInteractor: UpdateNomineeInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateNomineeListener<View>, UpdateNomineeInteractor.OnCallHitListener {

    override fun postUpdateNomineeApiCall() {
        var updateNomineeRequestModel = UpdateNomineeRequestModel(getBaseMvpVieww().getUserId(), getBaseMvpVieww().getNomineeName(), getBaseMvpVieww().getNomineePhoneNumber(), getBaseMvpVieww().getNomineeAddress(), getBaseMvpVieww().getNomineeRelations())
        updateNomineeInteractor.postUpdateNomineeDataToServer(updateNomineeRequestModel, this)
    }

    override fun onSuccessUpdateNomineeInteractListener(updateNomineeResponseModel: UpdateNomineeResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().navigateToMainScreen()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(updateNomineeResponseModel.message))
    }

    override fun onRetrofitFailureUpdateNomineeInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireUpdateNomineeInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorUpdateNomineeInteractListener(updateNomineeResponseModel: UpdateNomineeResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(updateNomineeResponseModel.message)
    }

    override fun onServerExceptionUpdateNomineeInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateNomineeDatas() {
        if (getBaseMvpVieww().getNomineeName().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateNomineeNameNullString))
        } else if (getBaseMvpVieww().getNomineePhoneNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateNomineePhoneNullString))
        } else if (getBaseMvpVieww().getNomineeAddress().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateNomineeAddressNullString))
        } else if (getBaseMvpVieww().getNomineeRelations().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateNomineeRelationNullString))
        } else {
            getBaseMvpVieww().postNomineeData()
        }
    }


}