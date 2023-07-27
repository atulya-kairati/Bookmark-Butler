package com.atulya.bookmarkbutler.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atulya.bookmarkbutler.database.models.LinkTag
import com.atulya.bookmarkbutler.utils.LINK_TAG_TABLE_NAME


@Dao
interface LinkTagDao {

    @Insert
    suspend fun addLinkTag(linkTag: LinkTag)

    @Query("SELECT tagId FROM $LINK_TAG_TABLE_NAME WHERE linkId=(:linkId)")
    suspend fun getTagIdsByLinkId(linkId: Int)

    @Delete
    suspend fun delete(linkTag: LinkTag)
}