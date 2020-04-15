package absa.cgs.com.ui.screens.apis.readprofileapicall

import absa.cgs.com.ui.screens.base.BaseMvpPresenter


interface IReadProfileListener<View : ReadProfileView> : BaseMvpPresenter<View> {

    fun readUserProfileData()

}
