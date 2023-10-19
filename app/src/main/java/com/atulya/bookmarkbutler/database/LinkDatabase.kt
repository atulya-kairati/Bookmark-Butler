package com.atulya.bookmarkbutler.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atulya.bookmarkbutler.database.dao.LinkDao
import com.atulya.bookmarkbutler.database.models.Link
import com.atulya.bookmarkbutler.database.models.LinkTagCrossRef
import com.atulya.bookmarkbutler.database.models.Tag


const val DB_NAME = "bookmarks"

@Database(entities = [Link::class, Tag::class, LinkTagCrossRef::class], version = 1)
abstract class LinkDatabase : RoomDatabase() {

    abstract fun linkDao(): LinkDao

}