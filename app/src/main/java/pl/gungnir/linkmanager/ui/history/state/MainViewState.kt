package pl.gungnir.linkmanager.ui.history.state

import pl.gungnir.linkmanager.domain.model.Link

data class MainViewState(
    var listLinks: List<Link>? = null
)