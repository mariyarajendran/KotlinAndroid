package absa.cgs.com.ui.screens.base

import android.content.Context

interface BaseMvpPresenter<V : BaseMvpView> {

    fun attachView(context: Context, baseMvpView: V)

    fun detachView()
}