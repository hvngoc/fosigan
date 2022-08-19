package com.waka.dana.na.domain.response

/**
 * Created by hvngoc on 7/29/22
 */
sealed class DataResult {
    class Success<T>(val data: T?) : DataResult()
    class Error(val e: Throwable) : DataResult()
}
