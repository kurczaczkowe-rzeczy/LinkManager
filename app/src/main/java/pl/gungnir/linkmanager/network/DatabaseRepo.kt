package pl.gungnir.linkmanager.network

import kotlinx.coroutines.flow.Flow
import pl.gungnir.linkmanager.domain.model.Result

interface DatabaseRepo {

    suspend fun getLinks(): Flow<Result>
}