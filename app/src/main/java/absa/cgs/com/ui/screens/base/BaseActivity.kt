package absa.cgs.com.ui.screens.base

import absa.cgs.com.MyApplication
import absa.cgs.com.di.component.ActivityComponent
import absa.cgs.com.di.component.DaggerActivityComponent
import absa.cgs.com.di.module.ActivityModule
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

    override fun navigationRoutes(routingClass: Class<*>) {
        val intent = Intent(this, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        this.startActivity(intent)
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


    override fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}