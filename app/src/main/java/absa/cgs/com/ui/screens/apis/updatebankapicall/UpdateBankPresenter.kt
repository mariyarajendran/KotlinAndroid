package absa.cgs.com.ui.screens.apis.updatebankapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankRequestModel
import absa.cgs.com.ui.screens.apis.updatebankapicall.model.UpdateBankResponseModel
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

class UpdateBankPresenter<View : UpdateBankView> @Inject constructor(var updateBankInteractor: UpdateBankInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateBankListener<View>, UpdateBankInteractor.OnCallHitListener {

    override fun postUpdateBankApiCall() {
        var updateBankRequestModel = UpdateBankRequestModel(getBaseMvpVieww().getUserId(), getBaseMvpVieww().getAccountNumber(), getBaseMvpVieww().getIfscCode(), getBaseMvpVieww().getAccountHolderName())
        updateBankInteractor.postUpdateBankDataToServer(updateBankRequestModel, this)
    }

    override fun onSuccessUpdateBankInteractListener(updateBankResponseModel: UpdateBankResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().navigateToMainScreen()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(updateBankResponseModel.message))
    }

    override fun onRetrofitFailureUpdateBankInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireUpdateBankInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorUpdateBankInteractListener(updateBankResponseModel: UpdateBankResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(updateBankResponseModel.message)
    }

    override fun onServerExceptionUpdateBankInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateBankDatas() {
        if (getBaseMvpVieww().getAccountNumber().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateBankAccountNoNullString))
        } else if (getBaseMvpVieww().getIfscCode().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateBankIfscNullString))
        } else if (getBaseMvpVieww().getAccountHolderName().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.updateBankAccountHolderNameNullString))
        } else {
            getBaseMvpVieww().postBankData()
        }
    }


}