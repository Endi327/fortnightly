package com.align.domain.usecases

import com.align.domain.entities.Article
import com.align.domain.usecases.common.IUseCase
import com.align.domain.usecases.parameters.GetArticlesParams

interface GetArticlesUseCase: IUseCase<GetArticlesParams, Pair<List<Article>, Int>>