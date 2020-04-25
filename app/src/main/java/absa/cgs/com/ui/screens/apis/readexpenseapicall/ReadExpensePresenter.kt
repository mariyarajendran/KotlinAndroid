package absa.cgs.com.ui.screens.apis.readexpenseapicall


import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseRequestModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import javax.inject.Inject

class ReadExpensePresenter<View : ReadExpenseView> @Inject constructor(var readExpenseInteractor: ReadExpenseInteractor) : BasePresenter<View>(), IReadExpenseListener<View>, ReadExpenseInteractor.OnCallHitListener {
    override fun readExpenseData() {
        val readExpenseRequestModel = ReadExpenseRequestModel(
                getBaseMvpVieww().getUserID(),
                getBaseMvpVieww().getSearchKeyword(),
                getBaseMvpVieww().getPageCount(),
                getBaseMvpVieww().getFromDate(),
                getBaseMvpVieww().getToDate())
        readExpenseInteractor.readExpenseDataToServer(readExpenseRequestModel, this)
    }

    override fun onSuccessReadExpenseInteractListener(readExpenseResponseModel: ReadExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().onSuccessReadExpenseResponse(readExpenseResponseModel)
    }

    override fun onRetrofitFailureReadExpenseInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireReadExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorReadExpenseInteractListener(readExpenseResponseModel: ReadExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(readExpenseResponseModel.message)
    }

    override fun onServerExceptionReadExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}
