package com.atulya.bookmarkbutler.database.models

import androidx.compose.runtime.toMutableStateList
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.atulya.bookmarkbutler.models.Bookmark

data class LinkWithTags(
    @Embedded val link: Link,
    @Relation(
        parentColumn = "linkId",
        entityColumn = "tagId",
        associateBy = Junction(LinkTagCrossRef::class)
    )
    val tags: List<Tag>
) {
    fun toBookmark() = Bookmark(
        name = link.title,
        url = link.url,
        description = link.description,
        tags = tags.map { it.tag }.toMutableStateList()
    )
}
