package absa.cgs.com

import android.app.Application
import android.content.Context

import absa.cgs.com.di.component.ApplicationComponent
import absa.cgs.com.di.component.DaggerApplicationComponent
import absa.cgs.com.di.module.ApplicationModule


class MyApplication : Application() {

    lateinit var component: ApplicationComponent

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

}
