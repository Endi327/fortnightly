package com.align.core

import android.content.Context
import com.align.common.showToastMessage
import com.align.domain.FortnightlyError

fun Context.showError(error: FortnightlyError) = when (error) {
    FortnightlyError.HttpError -> showToastMessage(R.string.generic_error)
    FortnightlyError.UnknownError -> showToastMessage(R.string.generic_error)
}