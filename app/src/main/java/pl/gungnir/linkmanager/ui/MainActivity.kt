package pl.gungnir.linkmanager.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.ui.history.HistoryFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showHistoryFragment()
    }

    fun showHistoryFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                HistoryFragment(),
                "HistoryFragment"
            )
            .commit()
    }
}