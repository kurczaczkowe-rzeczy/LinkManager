package pl.gungnir.linkmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.gungnir.linkmanager.domain.model.None
import pl.gungnir.linkmanager.domain.model.Result
import pl.gungnir.linkmanager.domain.useCase.GetLinkUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLinkUseCase: GetLinkUseCase
) : ViewModel() {

    private var links: Result = Result.Empty

    init {
        if (links is Result.Empty) {
            links = Result.Loading
            viewModelScope.launch {
                getLinkUseCase.invoke(None)
                    .onEach {
                        links = it
                    }
                    .launchIn(this)
            }
        }
    }

}