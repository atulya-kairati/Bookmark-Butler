package com.atulya.bookmarkbutler.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atulya.bookmarkbutler.utils.LINK_TABLE_NAME

@Entity(tableName = LINK_TABLE_NAME)
data class Link(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val url: String,
    val description: String?,
)
