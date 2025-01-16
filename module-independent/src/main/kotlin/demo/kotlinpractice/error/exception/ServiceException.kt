package demo.kotlinpractice.error.exception

import demo.kotlinpractice.type.ErrorCode

open class ServiceException(
    val errorCode: ErrorCode
) : RuntimeException() {
}
