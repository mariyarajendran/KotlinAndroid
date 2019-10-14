package absa.cgs.com.kotlinplayground

import absa.cgs.com.utils.CommonUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), MainView {

    var commonUtils = CommonUtils(this)
    private val presenterMain = MainPresenter(this, MainInteractor())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_play_ground)
        presenterMain.validateFirstTrigger("samples", "")

    }


    override fun onSuccessResponse(message: String) {
        commonUtils.showToastSmall(message)
    }

    override fun onFailureResponse(error: String) {
        commonUtils.showToastSmall(error)
    }
}
