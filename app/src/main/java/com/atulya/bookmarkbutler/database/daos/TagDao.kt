package com.atulya.bookmarkbutler.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atulya.bookmarkbutler.database.models.Tag
import com.atulya.bookmarkbutler.utils.TAG_TABLE_NAME

@Dao
interface TagDao {

    @Insert
    suspend fun insertTag(tag: Tag)

    @Query("SELECT tag FROM $TAG_TABLE_NAME WHERE id=(:id)")
    suspend fun getTag(id: Int)

    @Delete
    suspend fun delete(tag: Tag)
}