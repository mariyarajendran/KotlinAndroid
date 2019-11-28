package absa.cgs.com.screens.mainbaseactivity


class MainPresenter(var mainView: MainView, val mainInteractor: MainInteractor) : MainInteractor.onFirstEventTriggerListener {


    fun validateFirstTrigger(username: String, password: String) {
        val mainRequest = MainRequest(0, "")
        mainInteractor.firstTriggerEvent(mainRequest, this)

    }


    override fun onSuccessInteractListener(message: String) {
        mainView.onSuccessResponse(message)

    }

    override fun onFailureInteractListener(error: String) {
        mainView.onFailureResponse(error)
    }
}