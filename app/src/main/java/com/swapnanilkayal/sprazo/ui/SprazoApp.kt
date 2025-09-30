package com.swapnanilkayal.sprazo.ui

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.activity.BackEventCompat
import androidx.activity.compose.BackHandler
import androidx.activity.compose.PredictiveBackHandler

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.swapnanilkayal.sprazo.Models.HomeViewModel
import com.swapnanilkayal.sprazo.Models.LessonViewModel
import com.swapnanilkayal.sprazo.Models.LessonViewModelFactory
import com.swapnanilkayal.sprazo.Models.OnboardingViewModel
import com.swapnanilkayal.sprazo.System.Prefs
import com.swapnanilkayal.sprazo.ui.ComposeScreens.HomeScreen
import com.swapnanilkayal.sprazo.ui.ComposeScreens.LessonScreen
import com.swapnanilkayal.sprazo.ui.ComposeScreens.OnBoardingScreen
import com.swapnanilkayal.sprazo.ui.ComposeScreens.SplashScreen
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow


object Routes {
    val splash = "splash"
    val onboarding = "onboarding"
    val home = "home"
    val lesson = "lesson/{sectionId}/{unitId}/{lessonId}/{difficulty}"
}

val routeOrder = listOf(
    Routes.splash,
    Routes.onboarding,
    Routes.home,
    Routes.lesson
)


fun routeIndex(route: String?): Int = routeOrder.indexOf(route)

@Composable
fun SprazoApp(navController: NavHostController = rememberNavController()) {
    val animationDuration = 1000

    NavHost(
        navController = navController,
        startDestination = if (Prefs.isOnboarded()) Routes.home else Routes.onboarding,
        enterTransition = {
            val initial = routeIndex(initialState.destination.route)
            val target = routeIndex(targetState.destination.route)

            if (target > initial) {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            } else {
                slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            }
        },
        exitTransition = {
            val initial = routeIndex(initialState.destination.route)
            val target = routeIndex(targetState.destination.route)

            if (target > initial) {
                slideOutVertically(
                    targetOffsetY = { -it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            } else {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            }
        },
        popEnterTransition = {
            val initial = routeIndex(initialState.destination.route)
            val target = routeIndex(targetState.destination.route)

            if (target > initial) {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            } else {
                slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            }
        },
        popExitTransition = {
            val initial = routeIndex(initialState.destination.route)
            val target = routeIndex(targetState.destination.route)

            if (target > initial) {
                slideOutVertically(
                    targetOffsetY = { -it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            } else {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = animationDuration)
                )
            }
        }
    ) {
        composable(Routes.splash) { backStackEntry ->
            SplashScreen { navController.navigate(it) { popUpTo(Routes.splash) { inclusive = true } } }
        }

        composable(
            Routes.onboarding,
        ) {
            var trnx by remember { mutableStateOf(false) }
            fun exit() {
                if (!navController.navigateUp()) {
                    (navController.context as? Activity)?.finish()
                }
            }
            val onboardingViewModel: OnboardingViewModel = viewModel()
            if (!isDeviceTablet()) LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            OnBoardingScreen(onboardingViewModel, {navController.navigate(Routes.home){popUpTo(Routes.onboarding){inclusive = true} } })







            PredictiveBackHandler(onboardingViewModel.page > 0 || trnx) { progress: Flow<BackEventCompat> ->
                val page = onboardingViewModel.page
                if (page > 0) {
                    trnx = true
                    try {
                        progress.collect { backEvent->
                            if (backEvent.progress > 0.25){
                                trnx = true
                                onboardingViewModel.page = page -1
                            }
                        }
                        onboardingViewModel.page = page -1
                        trnx = false
                    } catch (e: CancellationException) {
                        onboardingViewModel.page = page
                        trnx = false
                        throw e
                    }
                } else {
                    progress.collect{}
                }
            }

        }
        composable(
            Routes.home,
        ) {
            fun exit() {
                if (!navController.navigateUp()) {
                    (navController.context as? Activity)?.finish()
                }
            }
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(homeViewModel, {navController.navigate(it)}, {})
        }
        composable(
            Routes.lesson,
            arguments = listOf(
                navArgument("sectionId") { type = NavType.StringType },
                navArgument("unitId") { type = NavType.StringType },
                navArgument("lessonId") { type = NavType.StringType },
                navArgument("difficulty") { type = NavType.IntType }
            )
        ) {
            val sectionId = it.arguments?.getString("sectionId")?.replace(".","/")
            val unitId = it.arguments?.getString("unitId")?.replace(".","/")
            val lessonId = it.arguments?.getString("lessonId")?.replace(".","/")
            val difficulty = it.arguments?.getInt("difficulty")

            if (sectionId != null && unitId != null && lessonId != null && difficulty != null) {
                val lessonViewMode: LessonViewModel = viewModel(factory = LessonViewModelFactory(sectionId, unitId, lessonId, difficulty))

                if (!isDeviceTablet()) LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                LessonScreen(lessonViewMode,{navController.navigateUp()})
            } else navController.navigateUp()

        }

    }

}