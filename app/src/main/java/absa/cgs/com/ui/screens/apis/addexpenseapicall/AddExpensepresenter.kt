package absa.cgs.com.ui.screens.apis.addexpenseapicall

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class AddExpensepresenter<View : AddExpenseView> @Inject constructor(var addExpenseInteractor: AddExpenseInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IAddExpenseListener<View>, AddExpenseInteractor.OnCallHitListener {

    override fun postAddExpenseApiCall() {
        var addExpenseRequestModel = AddExpenseRequestModel(getBaseMvpVieww().getUserID(), getBaseMvpVieww().getExpenseAmount(), getBaseMvpVieww().getExpenseComment(), getBaseMvpVieww().getExpenseType(), getBaseMvpVieww().getExpenseUserDate())
        addExpenseInteractor.postAddExpenseDataToServer(addExpenseRequestModel, this)
    }

    override fun onSuccessAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel) {
        commonUtils.showToastLong(addExpenseResponseModel.message)

    }

    override fun onRetrofitFailureAddExpenseInteractListener(error: String) {

    }

    override fun onSessionExpireAddExpenseInteractListener() {

    }

    override fun onErrorAddExpenseInteractListener(addExpenseResponseModel: AddExpenseResponseModel) {

    }

    override fun onServerExceptionAddExpenseInteractListener() {

    }
}