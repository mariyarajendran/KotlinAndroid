package absa.cgs.com.ui.screens.apis.updateimagesapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageRequestModel
import absa.cgs.com.ui.screens.apis.updateimagesapicall.model.UpdateImageResponseModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeRequestModel
import absa.cgs.com.ui.screens.apis.updatenomineeapicall.model.UpdateNomineeResponseModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileRequestModel
import absa.cgs.com.ui.screens.apis.updateprofileapicall.model.UpdateProfileResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateImagePresenter<View : UpdateImageView> @Inject constructor(var updateImageInteractor: UpdateImageInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateImageListener<View>, UpdateImageInteractor.OnCallHitListener {

    override fun postUpdateImageApiCall() {
        var updateImageRequestModel = UpdateImageRequestModel(getBaseMvpVieww().getUserId(), getBaseMvpVieww().getImage(), getBaseMvpVieww().getImageTag())
        updateImageInteractor.postUpdateImageDataToServer(updateImageRequestModel, this)
    }

    override fun onSuccessUpdateImageInteractListener(updateImageResponseModel: UpdateImageResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        //getBaseMvpVieww().navigateToMainScreen()
        getBaseMvpVieww().hitGetProfileCall()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(updateImageResponseModel.message))
    }

    override fun onRetrofitFailureUpdateImageInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireUpdateImageInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorUpdateImageInteractListener(updateImageResponseModel: UpdateImageResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(updateImageResponseModel.message)
    }

    override fun onServerExceptionUpdateImageInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}