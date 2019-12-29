package absa.cgs.com.ui.screens.base

import android.app.Activity
import android.content.Context

interface BaseMvpPresenter<V : BaseMvpView> {

    fun attachView(context: Context, baseMvpView: V)

    fun attachView(activity: Activity, baseMvpView: V)

    fun detachView()
}