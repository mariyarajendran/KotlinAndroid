package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class ReadProfilePresenter<View : ReadProfileView> @Inject constructor(var readProfileInteractor: ReadProfileInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IReadProfileListener<View>, ReadProfileInteractor.OnCallHitListener {
    override fun readUserProfileData() {
        val readProfileRequestModel = ReadProfileRequestModel(getBaseMvpVieww().getUserID())
        readProfileInteractor.getReadprofileDataToServer(readProfileRequestModel, this)
    }

    override fun onSuccessReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().onSuccessReadProfileResponse(readProfileResponseModel)
    }

    override fun onRetrofitFailureReadProfileInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireReadProfileInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorReadProfileInteractListener(readProfileResponseModel: ReadProfileResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(readProfileResponseModel.message)
    }

    override fun onServerExceptionReadProfileInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}
