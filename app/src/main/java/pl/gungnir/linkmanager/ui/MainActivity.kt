package pl.gungnir.linkmanager.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.ui.browser.BrowserFragment
import pl.gungnir.linkmanager.ui.history.HistoryFragment
import pl.gungnir.linkmanager.ui.history.state.MainStateEvent

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showHistoryFragment()
    }

    fun showBrowserFragment() {
        displayFragment(
            BrowserFragment(),
            "BrowserFragment"
        )
    }

    private fun showHistoryFragment() {
        displayFragment(
            HistoryFragment(),
            "HistoryFragment"
        )
    }

    private fun displayFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                fragment,
                tag
            )
            .addToBackStack(tag)
            .commit()
    }
}