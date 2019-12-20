package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.ui.screens.base.BaseMvpPresenter

interface MainPresenterListener<View : MainView> : BaseMvpPresenter<View> {

    fun getDataFromServer()

}