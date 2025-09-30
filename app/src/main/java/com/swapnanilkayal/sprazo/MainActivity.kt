package com.swapnanilkayal.sprazo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.swapnanilkayal.sprazo.Data.Repositories
import com.swapnanilkayal.sprazo.System.Prefs
import com.swapnanilkayal.sprazo.ui.SprazoApp
import com.swapnanilkayal.sprazo.ui.theme.SprazoTheme
import com.swapnanilkayal.sprazo.ui.theme.setStatusBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        Prefs.init(this)
        Repositories.init(this)
        setTheme(R.style.Theme_Sprazo)
        enableEdgeToEdge()
        setContent {
            val theme = when(Prefs.Reactive.theme){
                "system" -> isSystemInDarkTheme()
                "light" -> false
                "dark" -> true
                else -> false
            }
            SprazoTheme(theme) {




                SprazoApp()
            }
        }
    }
}