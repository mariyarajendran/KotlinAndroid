package absa.cgs.com.ui.screens.apis.readexpenseapicall

import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginRequestModel
import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseRequestModel
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileRequestModel
import absa.cgs.com.ui.screens.apis.readprofileapicall.model.ReadProfileResponseModel
import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject

class ReadExpensePresenter<View : ReadExpenseView> @Inject constructor(var readExpenseInteractor: ReadExpenseInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), IReadExpenseListener<View>, ReadExpenseInteractor.OnCallHitListener {
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
        commonUtils.showToastLong(readExpenseResponseModel.product_details[0].expense_comment)

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
