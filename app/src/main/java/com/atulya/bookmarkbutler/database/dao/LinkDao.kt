package com.atulya.bookmarkbutler.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.atulya.bookmarkbutler.database.models.Link
import com.atulya.bookmarkbutler.database.models.LinkTagCrossRef
import com.atulya.bookmarkbutler.database.models.LinkWithTags
import com.atulya.bookmarkbutler.database.models.Tag
import com.atulya.bookmarkbutler.database.models.TagWithLinks
import com.atulya.bookmarkbutler.utils.LINK_TABLE_NAME
import com.atulya.bookmarkbutler.utils.LINK_TAG_TABLE_NAME
import com.atulya.bookmarkbutler.utils.TAG_TABLE_NAME

@Dao
interface LinkDao {

    @Query("SELECT * FROM $LINK_TABLE_NAME")
    suspend fun getAllLinks(): List<Link>

//    @Insert
//    suspend fun insertLink(link: Link): Int
//
//    @Query("SELECT * FROM $LINK_TABLE_NAME WHERE linkId=(:id)")
//    suspend fun getLinkById(id: Int): Link
//
//    @Update
//    suspend fun updateLink(link: Link)
//
//    @Delete
//    suspend fun deleteLink(id: Int)

    @Transaction
    @Query("SELECT * FROM $LINK_TABLE_NAME")
    suspend fun getLinkWithTags(): List<LinkWithTags>

    @Transaction
    @Query("SELECT * FROM $TAG_TABLE_NAME")
    suspend fun getTagWithLinks(): List<TagWithLinks>

    @Query("SELECT tagId from $TAG_TABLE_NAME WHERE tag = :tag")
    suspend fun getTagId(tag: String): Int?

    @Query(
        """
            SELECT * FROM $LINK_TABLE_NAME
            WHERE linkId IN (SELECT linkId FROM $LINK_TAG_TABLE_NAME WHERE tagId = :tagId)
        """
    )
    suspend fun getLinksByTag(tagId: Int): List<LinkWithTags>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLink(link: Link): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: Tag): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLinkTagCrossRef(linkTagCrossRef: LinkTagCrossRef)

    @Transaction
    suspend fun insertLinkWithTags(lwt: LinkWithTags) {
        val linkId = insertLink(lwt.link)

        for (tag in lwt.tags) {
            val tagId = insertTag(tag)

            insertLinkTagCrossRef(LinkTagCrossRef(linkId = linkId.toInt(), tagId = tagId.toInt()))
        }
    }
}