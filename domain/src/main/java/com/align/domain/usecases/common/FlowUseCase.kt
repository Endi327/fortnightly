package com.align.domain.usecases.common

import com.align.domain.FortnightlyError
import com.align.domain.toFortnightlyError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber

@Suppress("USELESS_CAST")
abstract class FlowUseCase<in Params, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) : IFlowUseCase<Params, R> {
    override operator fun invoke(parameters: Params): Flow<UseCaseResult<FortnightlyError, R>> = flow {
        emitAll(execute(parameters))
    }
        .map { UseCaseResult.Success(it) as UseCaseResult<FortnightlyError, R> }
        .catch { e ->
            Timber.e(e)
            emit(UseCaseResult.Failure(e.toFortnightlyError()))
        }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: Params): Flow<R>
}

interface IFlowUseCase<in Params, R> {
    operator fun invoke(parameters: Params): Flow<UseCaseResult<FortnightlyError, R>>
}
