package com.atulya.bookmarkbutler.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.atulya.bookmarkbutler.database.models.Link
import com.atulya.bookmarkbutler.utils.LINK_TABLE_NAME

@Dao
interface LinkDao {

    @Query("SELECT * FROM $LINK_TABLE_NAME")
    suspend fun getAllLinks(): List<Link>

    @Insert
    suspend fun insertLink(link: Link): Int

    @Query("SELECT * FROM $LINK_TABLE_NAME WHERE id=(:id)")
    suspend fun getLinkById(id: Int): Link

    @Update
    suspend fun updateLink(link: Link)

    @Delete
    suspend fun deleteLink(id: Int)
}