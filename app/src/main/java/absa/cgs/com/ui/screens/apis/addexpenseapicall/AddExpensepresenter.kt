package absa.cgs.com.ui.screens.apis.addexpenseapicall

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class AddExpensepresenter<View : AddExpenseView> @Inject constructor(var addExpenseInteractor: AddExpenseInteractor, private val commonUtils: CommonUtils) : BasePresenter<View>(), IAddExpenseListener<View>, AddExpenseInteractor.OnCallHitListener {


    override fun postAddExpenseApiCall() {
        var addExpenseRequestModel = AddExpenseRequestModel(getBaseMvpVieww().getUserID(), getBaseMvpVieww().getExpenseAmount(), getBaseMvpVieww().getExpenseComment(), getBaseMvpVieww().getExpenseType(), getBaseMvpVieww().getExpenseUserDate())
        addExpenseInteractor.postAddExpenseDataToServer(addExpenseRequestModel, this)
    }

    override fun onSuccessAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().clearAllExpenseDetails()
        getBaseMvpVieww().navigateToGetExpenseDetails()
        getBaseMvpVieww().showToastLong(commonUtils.cutNull(addExpenseResponseModel.message))
    }

    override fun onRetrofitFailureAddExpenseInteractListener(error: String) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(error)
    }

    override fun onSessionExpireAddExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        activity!!.finishAffinity()
        getBaseMvpVieww().navigationRoutes(AuthenticationBaseActivity::class.java)
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.SessionTokenExpire))
    }

    override fun onErrorAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel) {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(addExpenseResponseModel.message)
    }

    override fun onServerExceptionAddExpenseInteractListener() {
        getBaseMvpVieww().hideProgressLoadingDialog()
        getBaseMvpVieww().showToastLong(getActivityy().resources.getString(R.string.ServerBusyString))
    }

    override fun validateAddExpense() {
        if (getBaseMvpVieww().getExpenseType().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseTypeNullString))
        } else if (getBaseMvpVieww().getExpenseComment().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseCommentNullString))
        } else if (getBaseMvpVieww().getExpenseAmount().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseAmountNullString))
        } else if (getBaseMvpVieww().getExpenseUserDate().equals("")) {
            getBaseMvpVieww().showToastLong(getActivityy().getString(R.string.AddExpenseDateNullString))
        } else {
            getBaseMvpVieww().postAddExpenseData()
        }
    }
}