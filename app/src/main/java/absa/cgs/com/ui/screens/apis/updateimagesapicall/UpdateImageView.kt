package absa.cgs.com.ui.screens.apis.updateimagesapicall

import absa.cgs.com.ui.screens.base.BaseMvpView

interface UpdateImageView : BaseMvpView {

    fun getUserId(): String
    fun getImage(): String
    fun getImageTag(): String
    fun navigateToMainScreen()
    fun postImageData()
    fun hitGetProfileCall()
    fun chooseImageFromGallery()
    fun chooseImageFromCamera()
    fun showImageUploadDialog()
}