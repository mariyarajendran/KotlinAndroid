package absa.cgs.com.ui.screens.apis.deleteexpenseapicall

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseRequestModel
import absa.cgs.com.ui.screens.apis.deleteexpenseapicall.model.DeleteExpenseResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class DeleteExpensepresenter<View : DeleteExpenseView> @Inject constructor(var deleteExpenseInteractor: DeleteExpenseInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IDeleteExpenseListener<View>, DeleteExpenseInteractor.OnCallHitListener {


    override fun postDeleteExpenseApiCall() {
        var deleteExpenseRequestModel = DeleteExpenseRequestModel(getBaseMvpVieww().getExpenseID())
        deleteExpenseInteractor.postDeleteExpenseDataToServer(deleteExpenseRequestModel, this)
    }

    override fun onSuccessAddExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel) {
        commonUtils.showToastLong(deleteExpenseResponseModel.message)
    }


    override fun onRetrofitFailureAddExpenseInteractListener(error: String) {

    }

    override fun onSessionExpireAddExpenseInteractListener() {

    }

    override fun onErrorAddExpenseInteractListener(deleteExpenseResponseModel: DeleteExpenseResponseModel) {
    }


    override fun onServerExceptionAddExpenseInteractListener() {

    }
}