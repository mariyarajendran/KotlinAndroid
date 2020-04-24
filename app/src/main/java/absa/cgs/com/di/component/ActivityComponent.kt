package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.di.module.ActivityModule
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.ui.screens.register.CustomerRegister
import absa.cgs.com.ui.screens.splash.SplashScreen
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import absa.cgs.com.utils.SessionUtils
import android.app.Activity
import android.content.Context
import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(customerRegister: CustomerRegister)
    fun inject(splashScreen: SplashScreen)
    fun inject(authenticationBaseActivity: AuthenticationBaseActivity)

    fun activity(): Activity



    fun commonUtils(): CommonUtils
    fun dialogUtils(): DialogUtils
    fun sessionUtils(): SessionUtils



}
