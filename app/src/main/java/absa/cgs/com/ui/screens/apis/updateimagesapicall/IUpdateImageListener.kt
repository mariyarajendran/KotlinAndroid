package absa.cgs.com.ui.screens.apis.updateimagesapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface IUpdateImageListener<View : UpdateImageView> : BaseMvpPresenter<View> {
    fun postUpdateImageApiCall()

}