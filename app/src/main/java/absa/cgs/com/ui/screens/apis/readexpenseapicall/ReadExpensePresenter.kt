package absa.cgs.com.ui.screens.apis.readexpenseapicall


import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseRequestModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
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
        getBaseMvpVieww().showExpenseToast(readExpenseResponseModel.product_details[0].expense_comment)


    }

    override fun onRetrofitFailureReadExpenseInteractListener(error: String) {

    }

    override fun onSessionExpireReadExpenseInteractListener() {

    }

    override fun onErrorReadExpenseInteractListener(readExpenseResponseModel: ReadExpenseResponseModel) {

    }

    override fun onServerExceptionReadExpenseInteractListener() {

    }


}
