package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseRequestModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class DeleteExpensepresenter<View : DeleteExpenseView> @Inject constructor(var deleteExpenseInteractor: DeleteExpenseInteractor, private var commonUtils: CommonUtils) : BasePresenter<View>(), IDeleteExpenseListener<View>, DeleteExpenseInteractor.OnCallHitListener {


    override fun postDeleteExpenseApiCall() {
        var deleteExpenseRequestModel = DeleteExpenseRequestModel(getBaseMvpVieww().getExpenseID())
        deleteExpenseInteractor.postDeleteExpenseDataToServer(deleteExpenseRequestModel, this)
    }

    override fun onSuccessDeleteExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().onSuccessDeleteExpenseResponse()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(deleteExpenseResponseModel.message))
        getBaseMvpVieww().hitExpenseDetailsCall()
    }

    override fun onRetrofitFailureDeleteExpenseInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireDeleteExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorDeleteExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(deleteExpenseResponseModel.message)
    }

    override fun onServerExceptionDeleteExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }


}