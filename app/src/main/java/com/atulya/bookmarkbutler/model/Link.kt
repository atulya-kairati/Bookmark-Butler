package com.atulya.bookmarkbutler.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Link(
    val name: String = "",
    val url: String = "",
    val description: String? = null,
    val tags: SnapshotStateList<String> = mutableStateListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Link

        if (name != other.name) return false
        if (url != other.url) return false
        if (description != other.description) return false
        if (tags.toString() != other.tags.toString()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + tags.hashCode()
        return result
    }
}