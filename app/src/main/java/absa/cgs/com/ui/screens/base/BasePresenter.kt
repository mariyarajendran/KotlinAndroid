package absa.cgs.com.ui.screens.base

import android.content.Context

class BasePresenter<V : BaseMvpView> : BaseMvpPresenter<V> {

    var baseMvpView: V? = null
    var context: Context? = null

    override fun attachView(context: Context, baseMvpView: V) {
        this.baseMvpView = baseMvpView
        this.context = context
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