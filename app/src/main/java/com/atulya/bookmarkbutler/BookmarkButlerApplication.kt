package com.atulya.bookmarkbutler

import android.app.Application
import android.util.Log
import com.atulya.bookmarkbutler.database.repository.LinkRepository

class BookmarkButlerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        LinkRepository.init(applicationContext)
        Log.d("#> ${this::class.simpleName}", "LinkRepository initialised")
    }
}