package com.atulya.bookmarkbutler.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Link(
    val name: String = "",
    val url: String = "",
    val description: String? = null,
    val tags: SnapshotStateList<String> = mutableStateListOf()
)