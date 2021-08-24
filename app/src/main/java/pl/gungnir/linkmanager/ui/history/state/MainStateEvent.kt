package pl.gungnir.linkmanager.ui.history.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write


    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

sealed class MainStateEvent {
    object GetLinks : MainStateEvent()
    class SelectLink(val position: Int) : MainStateEvent()
    object None : MainStateEvent()
}
