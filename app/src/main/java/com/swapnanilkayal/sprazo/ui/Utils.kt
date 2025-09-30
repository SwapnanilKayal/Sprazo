package com.swapnanilkayal.sprazo.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext


@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current

    val activity = remember(context) { context as? Activity }

    DisposableEffect(activity) {

        val original = activity?.requestedOrientation
        activity?.requestedOrientation = orientation

        onDispose {

            if (activity != null && original != null) {
                activity.requestedOrientation = original
            }
        }
    }
}

const val TABLET_MINIMUM_SMALLEST_SCREEN_WIDTH_DP = 600

@Composable
fun isDeviceTablet(): Boolean {
    val configuration = LocalConfiguration.current

    val smallestScreenWidthDp = configuration.smallestScreenWidthDp
    return remember(smallestScreenWidthDp) {
        smallestScreenWidthDp >= TABLET_MINIMUM_SMALLEST_SCREEN_WIDTH_DP
    }
}


@Composable
fun isCurrentLayoutTabletOptimized(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return remember(screenWidthDp) {
        screenWidthDp >= TABLET_MINIMUM_SMALLEST_SCREEN_WIDTH_DP
    }
}