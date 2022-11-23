package com.example.formulaone.data.model.links

import com.example.formulaone.domain.model.LinksDomain
import com.example.formulaone.ui.models.Links

data class Article(
    var id: String,
    var link: String
)

fun Article.toLinksDomain(): LinksDomain {
    return LinksDomain(
        id, link
    )
}