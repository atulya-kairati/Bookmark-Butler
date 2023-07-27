package com.atulya.bookmarkbutler.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Bookmark(
    val name: String = "",
    val url: String = "",
    val description: String? = null,
    val tags: SnapshotStateList<String> = mutableStateListOf()
)