package pl.gungnir.linkmanager.domain.useCase

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import pl.gungnir.linkmanager.domain.model.None
import pl.gungnir.linkmanager.domain.model.Result
import pl.gungnir.linkmanager.network.DatabaseRepo
import javax.inject.Inject

@ViewModelScoped
class GetLinkUseCase @Inject constructor(
    private val databaseRepo: DatabaseRepo
) : BaseUseCase<Result, None>() {

    override suspend fun run(params: None): Result {
        return databaseRepo.getLinks()
            .first()
    }
}