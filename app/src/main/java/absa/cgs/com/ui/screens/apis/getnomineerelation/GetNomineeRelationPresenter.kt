package absa.cgs.com.ui.screens.apis.getnomineerelation

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.getnomineerelation.model.NomineeRelationResponseModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class GetNomineeRelationPresenter<View : NomineeRelationView> @Inject constructor(var getNomineeRelationInteractor: GetNomineeRelationInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), INomineeRelationListener<View>, GetNomineeRelationInteractor.OnCallHitListener {

    override fun getNomineeRelationsData() {
        getNomineeRelationInteractor.getNomineeRelationDataToServer(this)
    }

    override fun onSuccessNomineeRelationInteractListener(nomineeRelationResponseModel: NomineeRelationResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().onSuccessGetNomineeResponse(nomineeRelationResponseModel)
    }

    override fun onRetrofitFailureNomineeRelationInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireNomineeRelationInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorNomineeRelationInteractListener(nomineeRelationResponseModel: NomineeRelationResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(nomineeRelationResponseModel.message)
    }

    override fun onServerExceptionNomineeRelationInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}
