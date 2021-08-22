package pl.gungnir.linkmanager.ui.history.state

sealed class MainStateEvent {

    object GetLinks : MainStateEvent()
    class SelectLink(val position: Int) : MainStateEvent()
    object None : MainStateEvent()
}
