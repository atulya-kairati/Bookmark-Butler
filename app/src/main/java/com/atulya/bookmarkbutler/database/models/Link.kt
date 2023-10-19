package com.atulya.bookmarkbutler.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.atulya.bookmarkbutler.utils.LINK_TABLE_NAME

@Entity(tableName = LINK_TABLE_NAME, indices = [Index(value = ["url"], unique = true)])
data class Link(
    @PrimaryKey(autoGenerate = true) val linkId: Int = 0,
    val title: String,

    val url: String,
    val description: String?,
)
