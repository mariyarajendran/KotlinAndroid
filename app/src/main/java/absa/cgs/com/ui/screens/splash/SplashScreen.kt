package absa.cgs.com.ui.screens.splash

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.utils.CommonUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import javax.inject.Inject

class SplashScreen : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        injection()
        init()
    }


    private fun init() {
        forSomeDelay()
    }

    private fun injection() {
        activityComponent().inject(this)
    }


    private fun forSomeDelay() {
        Handler().postDelayed({
            navigationRoutes(AuthenticationBaseActivity::class.java)
        }, 1000)
    }
}
