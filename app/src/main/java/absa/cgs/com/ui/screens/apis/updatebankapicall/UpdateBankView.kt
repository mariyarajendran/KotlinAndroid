package absa.cgs.com.ui.screens.apis.updatebankapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface UpdateBankView : BaseMvpView {

    fun getUserId(): String
    fun getAccountNumber(): String
    fun getIfscCode(): String
    fun getAccountHolderName(): String
    fun navigateToMainScreen()
    fun postBankData()

}