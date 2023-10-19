package com.atulya.bookmarkbutler.database.repository

import android.content.Context
import androidx.room.Room
import com.atulya.bookmarkbutler.database.DB_NAME
import com.atulya.bookmarkbutler.database.LinkDatabase
import com.atulya.bookmarkbutler.models.Bookmark

class LinkRepository private constructor(
    context: Context
) {

    private val database = Room.databaseBuilder(
        context.applicationContext,
        LinkDatabase::class.java,
        DB_NAME
    )
//        .createFromAsset("$DB_NAME.db")
        .build()


    suspend fun getBookmarks() = database.linkDao()
        .getLinkWithTags()
        .map { it.toBookmark() }

    suspend fun getTagsWithLinks() = database.linkDao()
        .getTagWithLinks()

    suspend fun getTagId(tag: String) = database.linkDao()
        .getTagId(tag)

    suspend fun getBookmarksByTag(tagId: Int) = database.linkDao()
        .getLinksByTag(tagId)
        .map { it.toBookmark() }

    suspend fun insertBookmark(bookmark: Bookmark) = database.linkDao()
        .insertLinkWithTags(bookmark.toLinkWithTags())

    companion object {
        private var INSTANCE: LinkRepository? = null

        fun init(context: Context) {
            if (INSTANCE != null) return

            INSTANCE = LinkRepository(context)

            INSTANCE!!.database.queryExecutor.execute {

            }
        }

        fun get() = checkNotNull(INSTANCE) {
            "Initialise LinkRepository first"
        }
    }
}