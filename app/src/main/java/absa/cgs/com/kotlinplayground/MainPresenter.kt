package absa.cgs.com.kotlinplayground

import org.json.JSONObject


class MainPresenter(var mainView: MainView, val mainInteractor: MainInteractor) : MainInteractor.onFirstEventTriggerListener {


    fun validateFirstTrigger(username: String, password: String) {
        val paramObject = JSONObject()
        paramObject.put("categoryId", 0)
        paramObject.put("Name", "")
        mainInteractor.firstTriggerEvent(paramObject, this)

    }


    override fun onSuccessInteractListener(message: String) {
        mainView.onSuccessResponse(message)

    }

    override fun onFailureInteractListener(error: String) {
        mainView.onFailureResponse(error)
    }
}