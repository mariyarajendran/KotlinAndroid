package absa.cgs.com.ui.screens.mainbaseactivity

import absa.cgs.com.ui.screens.base.BasePresenter
import absa.cgs.com.utils.CommonUtils
import javax.inject.Inject


class MainPresenter<View : MainView> @Inject constructor(var mainInteractor: MainInteractor, var commonUtils: CommonUtils) : BasePresenter<View>(), MainPresenterListener<View>, MainInteractor.onFirstEventTriggerListener {


    override fun getDataFromServer() {
        val mainRequest = MainRequest(0, "")
        commonUtils.showToastSmall(getBaseMvpVieww().getStringCheck())
        mainInteractor.firstTriggerEvent(mainRequest, this)
    }

    override fun onSuccessInteractListener(message: String) {
        getBaseMvpVieww().onSuccessResponse(message)
    }

    override fun onFailureInteractListener(error: String) {
        getBaseMvpVieww().onFailureResponse(error)
    }


}