package com.align.domain.usecases.common

import com.align.domain.FortnightlyError
import com.align.domain.toFortnightlyError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in Params, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) : IUseCase<Params, R> {
    override suspend operator fun invoke(parameters: Params): UseCaseResult<FortnightlyError, R> {
        return runCatching {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        }.fold(
            onSuccess = {
                UseCaseResult.Success(it)
            },
            onFailure = {
                Timber.e(it)
                UseCaseResult.Failure(it.toFortnightlyError())
            }
        )
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): R
}

interface IUseCase<in Params, R> {
    suspend operator fun invoke(parameters: Params): UseCaseResult<FortnightlyError, R>
}
