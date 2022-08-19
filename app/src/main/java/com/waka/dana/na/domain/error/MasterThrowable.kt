package com.waka.dana.na.domain.error

import com.waka.dana.na.domain.response.CommonError

/**
 * Created by hvngoc on 7/29/22
 */
class MasterThrowable(error: CommonError) : Throwable(message = error.message) {
}