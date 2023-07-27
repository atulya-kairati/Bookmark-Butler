package com.atulya.bookmarkbutler.database.models

import androidx.room.Entity
import com.atulya.bookmarkbutler.utils.LINK_TAG_TABLE_NAME

@Entity(tableName = LINK_TAG_TABLE_NAME, primaryKeys = ["linkId", "tagId"])
data class LinkTag(
    val linkId: Int,
    val tagId: Int,
)
