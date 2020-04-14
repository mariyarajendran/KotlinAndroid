package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseRequestModel
import absa.cgs.com.ui.screens.apis.addexpenseapicall.model.AddExpenseResponseModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class UpdateExpensepresenter<View : UpdateExpenseView> @Inject constructor(var updateExpenseInteractor: UpdateExpenseInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IUpdateExpenseListener<View>, UpdateExpenseInteractor.OnCallHitListener {
    override fun postUpdateExpenseApiCall() {
        var updateExpenseRequestModel = UpdateExpenseRequestModel(getBaseMvpVieww().getExpenseID(), getBaseMvpVieww().getExpenseAmount(), getBaseMvpVieww().getExpenseComment(), getBaseMvpVieww().getExpenseType(), getBaseMvpVieww().getExpenseUserDate())
        updateExpenseInteractor.postUpdateExpenseDataToServer(updateExpenseRequestModel, this)
    }

    override fun onSuccessUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {
        commonUtils.showToastLong(updateExpenseResponseModel.message)
    }

    override fun onRetrofitFailureUpdateExpenseInteractListener(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSessionExpireUpdateExpenseInteractListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onServerExceptionUpdateExpenseInteractListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}