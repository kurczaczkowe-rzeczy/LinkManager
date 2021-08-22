package pl.gungnir.linkmanager.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Type

    suspend operator fun invoke(
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): Type {
        return withContext(dispatcher) {
            run(params)
        }
    }
}