package absa.cgs.com.screens.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {


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
}