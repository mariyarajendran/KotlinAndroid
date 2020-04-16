package absa.cgs.com.ui.screens.base

import absa.cgs.com.MyApplication
import absa.cgs.com.di.component.DaggerFragmentComponent
import absa.cgs.com.di.component.FragmentComponent
import absa.cgs.com.di.module.FragmentModule
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment(), BaseMvpView {

    override fun showToastShort(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    override fun showToastLong(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    private var mFragmentComponent: FragmentComponent? = null

    override fun showLoadingDialog() {

    }

    override fun hideLoadingDialgo() {

    }

    override fun navigationRoutes(routingClass: Class<*>) {
        val intent = Intent(activity, routingClass)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }


    fun fragmentComponent(): FragmentComponent {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule(this))
                    .applicationComponent(MyApplication.get(context!!).component)
                    .build()
        }
        return mFragmentComponent!!
    }

}