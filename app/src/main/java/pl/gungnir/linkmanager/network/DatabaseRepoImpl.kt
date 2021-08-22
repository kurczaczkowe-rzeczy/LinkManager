package pl.gungnir.linkmanager.network

import kotlinx.coroutines.flow.Flow
import pl.gungnir.linkmanager.domain.model.Result
import javax.inject.Inject

class DatabaseRepoImpl @Inject constructor(
    private val firebaseDatabaseRepo: FirebaseDatabaseRepo
) : DatabaseRepo {

    override suspend fun getLinks(): Flow<Result> {
        return firebaseDatabaseRepo.getLinks()
    }
}