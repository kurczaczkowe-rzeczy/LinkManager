package pl.gungnir.linkmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import pl.gungnir.linkmanager.R
import pl.gungnir.linkmanager.network.DatabaseRepo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseRepo: DatabaseRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MRMRMR", "MainActivity.kt onCreate: ${databaseRepo.dump()}")
    }
}