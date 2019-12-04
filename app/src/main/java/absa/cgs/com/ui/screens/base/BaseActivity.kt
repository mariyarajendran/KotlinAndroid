package absa.cgs.com.ui.screens.base

import absa.cgs.com.MyApplication
import absa.cgs.com.di.component.ActivityComponent
import absa.cgs.com.di.component.DaggerActivityComponent
import absa.cgs.com.di.module.ActivityModule
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity(), BaseMvpView {

    private var mActivityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onStop() {
        super.onStop()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    override fun hideLoadingDialgo() {

    }

    override fun showLoadingDialog() {

    }

    fun activityComponent(): ActivityComponent {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(ActivityModule(this))
                    .applicationComponent(MyApplication.get(this).component)
                    .build()
        }
        return mActivityComponent!!
    }

}