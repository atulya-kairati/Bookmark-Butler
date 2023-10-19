package com.atulya.bookmarkbutler.models

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.atulya.bookmarkbutler.database.models.Link
import com.atulya.bookmarkbutler.database.models.LinkWithTags
import com.atulya.bookmarkbutler.database.models.Tag

data class Bookmark(
    val name: String = "",
    val url: String = "",
    val description: String? = null,
    val tags: SnapshotStateList<String> = mutableStateListOf()
) {

    fun toLinkWithTags() = LinkWithTags(
        link = Link(title = name, url = url, description = description),
        tags = tags.map { Tag(tag = it) }.toList()
    )
    override fun toString(): String {
        return "Bookmark(name='$name', url='$url', description=$description, tags=${tags.toList()})"
    }
}