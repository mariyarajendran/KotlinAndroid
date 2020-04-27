package absa.cgs.com.ui.screens.apis.updatebankapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IUpdateBankListener<View : UpdateBankView> : BaseMvpPresenter<View> {
    fun postUpdateBankApiCall()
    fun validateBankDatas()
}