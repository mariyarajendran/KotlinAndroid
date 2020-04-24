package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateExpensePresenter<View : UpdateExpenseView> @Inject constructor(var updateExpenseInteractor: UpdateExpenseInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateExpenseListener<View>, UpdateExpenseInteractor.OnCallHitListener {


    override fun postUpdateExpenseApiCall() {
        var updateExpenseRequestModel = UpdateExpenseRequestModel(getBaseMvpVieww().getExpenseID(), getBaseMvpVieww().getExpenseAmount(), getBaseMvpVieww().getExpenseComment(), getBaseMvpVieww().getExpenseType(), getBaseMvpVieww().getExpenseUserDate())
        updateExpenseInteractor.postUpdateExpenseDataToServer(updateExpenseRequestModel, this)
    }

    override fun onSuccessUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().navigateToGetExpenseDetails()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(updateExpenseResponseModel.message))
    }

    override fun onRetrofitFailureUpdateExpenseInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireUpdateExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(updateExpenseResponseModel.message)
    }

    override fun onServerExceptionUpdateExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateEditExpense() {
        if (getBaseMvpVieww().getExpenseType().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseTypeNullString))
        } else if (getBaseMvpVieww().getExpenseComment().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseCommentNullString))
        } else if (getBaseMvpVieww().getExpenseAmount().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseAmountNullString))
        } else if (getBaseMvpVieww().getExpenseUserDate().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseDateNullString))
        } else {
            getBaseMvpVieww().postEditExpenseData()
        }
    }

}