package pl.gungnir.linkmanager.domain.useCase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseFlowUseCase<out Type, in Params> where Type : Any {

    abstract fun run(params: Params): Flow<Type>

    @ExperimentalCoroutinesApi
    operator fun invoke(
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ): Flow<Type> {
        return run(params).flowOn(dispatcher)
    }
}