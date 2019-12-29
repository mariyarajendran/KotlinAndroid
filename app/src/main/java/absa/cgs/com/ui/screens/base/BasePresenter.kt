package absa.cgs.com.ui.screens.base

import android.app.Activity
import android.content.Context

open class BasePresenter<V : BaseMvpView> : BaseMvpPresenter<V> {

    var baseMvpView: V? = null
    var context: Context? = null
    var activity: Activity? = null

    override fun attachView(context: Context, baseMvpView: V) {
        this.baseMvpView = baseMvpView
        this.context = context
    }

    override fun attachView(activity: Activity, baseMvpView: V) {
        this.baseMvpView = baseMvpView
        this.activity = activity
    }

    override fun detachView() {
        baseMvpView = null
    }

    fun getBaseMvpVieww(): V {
        return baseMvpView!!
    }


    fun getContextt(): Context {
        return context!!
    }

    fun getActivityy(): Activity {
        return activity!!
    }

    fun isViewAttached(): Boolean {
        return baseMvpView != null
    }

    fun checkViewAttached() {
        if (!isViewAttached()) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call " +
            "Presenter.attachView(MvpView) before"
            + " requesting data to the Presenter")

}