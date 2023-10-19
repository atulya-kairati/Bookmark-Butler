package com.atulya.bookmarkbutler.ui.pages.addurl

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.atulya.bookmarkbutler.database.repository.LinkRepository
import com.atulya.bookmarkbutler.models.Bookmark

class AddLinkPageViewModel : ViewModel() {

    var bookmark by mutableStateOf(Bookmark())

    suspend fun saveBookmark() {
        LinkRepository.get().insertBookmark(bookmark)

        Log.d("#> ${this::class.simpleName}", " : ${LinkRepository.get().getBookmarks()}")
    }
}