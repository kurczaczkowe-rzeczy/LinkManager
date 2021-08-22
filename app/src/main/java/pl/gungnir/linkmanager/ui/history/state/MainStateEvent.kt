package pl.gungnir.linkmanager.ui.history.state

sealed class MainStateEvent {

    object GetLinks : MainStateEvent()
    object None : MainStateEvent()
}
