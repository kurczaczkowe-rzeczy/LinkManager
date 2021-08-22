package pl.gungnir.linkmanager.domain.model

import java.util.Date

data class Link(
    val label: String,
    val url: String,
    val visited: Date?
)
