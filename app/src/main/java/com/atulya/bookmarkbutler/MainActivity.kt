package com.atulya.bookmarkbutler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atulya.bookmarkbutler.ui.pages.addurl.AddLinkPage
import com.atulya.bookmarkbutler.ui.pages.home.HomePage
import com.atulya.bookmarkbutler.ui.theme.BookmarkButlerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookmarkButlerApp()
        }
    }
}

@Composable
fun BookmarkButlerApp() {
    BookmarkButlerTheme {
        // A surface container using the 'background' color from the theme

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.home,
        ) {

            composable(route = Routes.home) {
                HomePage(
                    onSearchClicked = {},
                    onSettingsClicked = {},
                    onAddClicked = { navController.navigateWithSingleTopTo(Routes.addLinkPage) },
                )
            }

            composable(route = Routes.addLinkPage) {
                AddLinkPage()
            }
        }
    }
}

fun NavHostController.navigateWithSingleTopTo(route: String) {
    val controller = this
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true

        popUpTo(controller.graph.startDestinationId) {
            saveState = true
        }
    }
}