package com.atulya.bookmarkbutler.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.atulya.bookmarkbutler.utils.TAG_TABLE_NAME

@Entity(tableName = TAG_TABLE_NAME, indices = [Index(value = ["tag"], unique = true)])
data class Tag(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tag: String,
)
