package com.example.formulaone.data.remote.news

import com.example.formulaone.domain.model.remote.NewsDtItemDomain

data class NewsDtItem(
    val imgsrc: String,
    val link: String,
    val shortdesc: String,
    val title: String
)
fun NewsDtItem.toNewsItemDomain(): NewsDtItemDomain{
    return NewsDtItemDomain(
        imgsrc, shortdesc, title
    )
}