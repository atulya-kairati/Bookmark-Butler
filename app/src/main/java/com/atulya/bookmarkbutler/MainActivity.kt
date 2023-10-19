package com.atulya.bookmarkbutler

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atulya.bookmarkbutler.database.repository.LinkRepository
import com.atulya.bookmarkbutler.models.Bookmark
import com.atulya.bookmarkbutler.ui.pages.addurl.AddLinkPage
import com.atulya.bookmarkbutler.ui.pages.home.HomePage
import com.atulya.bookmarkbutler.ui.theme.BookmarkButlerTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookmarkButlerApp()
        }

        GlobalScope.launch {
            val b = LinkRepository.get().getBookmarks()
            Log.d("#> ${this::class.simpleName}", "$b")

            LinkRepository.get().insertBookmark(Bookmark(
                name = "ABC",
                url = "www.abc.com",
                description = "abc",
                tags = mutableStateListOf("A", "Free", "F")
            ))

            val d = LinkRepository.get().getBookmarks()
            Log.d("#> ${this::class.simpleName}", "$d")
//
            LinkRepository.get().getTagId("mail")?.let {
                val t = LinkRepository.get().getBookmarksByTag(it)
                Log.d("#> ${this::class.simpleName}", "$t")
            }
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
                AddLinkPage(
                    onSaved = {navController.popBackStack()}
                )
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