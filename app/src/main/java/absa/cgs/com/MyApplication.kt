package absa.cgs.com

import android.app.Application
import android.content.Context

import absa.cgs.com.di.component.ApplicationComponent
import absa.cgs.com.di.component.DaggerApplicationComponent
import absa.cgs.com.di.component.FragmentComponent
import absa.cgs.com.di.module.ApplicationModule
import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MyApplication : Application() {


    lateinit var component: ApplicationComponent


//    companion object {
//        lateinit var component: ApplicationComponent
//    }


    override fun onCreate() {
        super.onCreate()
        createApplicationComponent()
    }


    private fun createApplicationComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }


    companion object {
        operator fun get(context: Context): MyApplication {
            return context.applicationContext as MyApplication
        }
    }


    fun getComponents(): ApplicationComponent {
        return component
    }


}
