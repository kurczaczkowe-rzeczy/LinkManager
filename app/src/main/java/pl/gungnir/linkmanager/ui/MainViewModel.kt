package pl.gungnir.linkmanager.ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.gungnir.linkmanager.domain.model.Link
import pl.gungnir.linkmanager.domain.model.None
import pl.gungnir.linkmanager.domain.model.Result
import pl.gungnir.linkmanager.domain.useCase.GetLinkUseCase
import pl.gungnir.linkmanager.ui.history.state.MainStateEvent
import pl.gungnir.linkmanager.ui.history.state.MainViewState
import pl.gungnir.linkmanager.uitl.AbsentLiveData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        return when (stateEvent) {
            is MainStateEvent.GetLinks -> {
                object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        viewModelScope.launch {
                            val result = getLinkUseCase.invoke(None)
                            if (result is Result.Success) {
                                value = MainViewState(
                                    listLinks = result.data
                                )
                            }
                        }
                    }
                }
            }

            MainStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setLinks(list: List<Link>) {
        val update = getCurrentViewStateOrNew()
        update.listLinks = list
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

    fun setStateEvent(event: MainStateEvent) {
        val state: MainStateEvent = event
        _stateEvent.value = state
    }

//    private var links: Result = Result.Empty

//    init {
//        if (links is Result.Empty) {
//            links = Result.Loading
//            viewModelScope.launch {
//                getLinkUseCase.invoke(None)
//                    .onEach {
//                        links = it
//                    }
//                    .launchIn(this)
//            }
//        }
//    }

}