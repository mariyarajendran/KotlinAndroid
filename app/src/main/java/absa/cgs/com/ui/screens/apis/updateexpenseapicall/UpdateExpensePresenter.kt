package absa.cgs.com.ui.screens.apis.updateexpenseapicall

import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseRequestModel
import absa.cgs.com.ui.screens.apis.updateexpenseapicall.model.UpdateExpenseResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import javax.inject.Inject

class UpdateExpensePresenter<View : UpdateExpenseView> @Inject constructor(var updateExpenseInteractor: UpdateExpenseInteractor) : BasePresenter<View>(), IUpdateExpenseListener<View>, UpdateExpenseInteractor.OnCallHitListener {


    override fun postUpdateExpenseApiCall() {
        var updateExpenseRequestModel = UpdateExpenseRequestModel(getBaseMvpVieww().getExpenseID(), getBaseMvpVieww().getExpenseAmount(), getBaseMvpVieww().getExpenseComment(), getBaseMvpVieww().getExpenseType(), getBaseMvpVieww().getExpenseUserDate())
        updateExpenseInteractor.postUpdateExpenseDataToServer(updateExpenseRequestModel, this)
    }

    override fun onSuccessUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {

    }

    override fun onRetrofitFailureUpdateExpenseInteractListener(error: String) {

    }

    override fun onSessionExpireUpdateExpenseInteractListener() {

    }

    override fun onErrorUpdateExpenseInteractListener(updateExpenseResponseModel: UpdateExpenseResponseModel) {

    }

    override fun onServerExceptionUpdateExpenseInteractListener(statusCode: Int) {

    }


}