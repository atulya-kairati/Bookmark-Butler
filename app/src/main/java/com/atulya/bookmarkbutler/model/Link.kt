package com.atulya.bookmarkbutler.model

data class Link(
    val name: String = "",
    val url: String = "",
    val description: String? = null,
    val tags: MutableList<String> = mutableListOf()
)