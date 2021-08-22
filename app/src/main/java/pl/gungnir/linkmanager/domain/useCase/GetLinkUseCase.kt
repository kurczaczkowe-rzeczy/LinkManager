package pl.gungnir.linkmanager.domain.useCase

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import pl.gungnir.linkmanager.domain.model.None
import pl.gungnir.linkmanager.domain.model.Result
import pl.gungnir.linkmanager.network.DatabaseRepo
import javax.inject.Inject

@ViewModelScoped
class GetLinkUseCase @Inject constructor(
    private val databaseRepo: DatabaseRepo
) : BaseFlowUseCase<Result, None>() {

    override suspend fun run(params: None): Flow<Result> {
        return databaseRepo.getLinks()
    }
}