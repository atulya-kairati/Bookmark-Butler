package com.atulya.bookmarkbutler.database.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagWithLinks(
    @Embedded val tag: Tag,

    @Relation(
        parentColumn = "tagId",
        entityColumn = "linkId",
        associateBy = Junction(LinkTagCrossRef::class)
    )
    val links: List<Link>
)