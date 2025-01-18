package demo.kotlinpractice.error.exception

import demo.kotlinpractice.error.ServiceException
import demo.kotlinpractice.type.ErrorCode

class MemberNotFoundException : ServiceException(ErrorCode.MEMBER_NOT_FOUND)
