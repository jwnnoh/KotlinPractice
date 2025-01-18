package demo.kotlinpractice.error

import demo.kotlinpractice.type.ErrorCode

open class ServiceException(val errorCode: ErrorCode) : RuntimeException()
