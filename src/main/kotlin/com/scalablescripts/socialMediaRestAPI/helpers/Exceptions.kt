package com.scalablescripts.socialMediaRestAPI.helpers

import java.util.*


class err400 {
    companion object {
        val BAD_REQUEST = "BAD_REQUEST"
    }
}

class err401 {
    companion object {
        val INVALID_TOKEN = "INVALID_TOKEN"

        val UNAUTHORIZED = "UNAUTHORIZED"

        val INVALID_CREDENTIALS= "INVALID_CREDENTIALS"
    }
}

class err403 {
    companion object {
        val FORBIDDEN = "FORBIDDEN"
    }
}

class err404 {
    companion object {
        val NOT_FOUND = "NOT_FOUND"
        val POST_NOT_FOUND = "POST NOT FOUND"
        val API_NOT_FOUND = "API_NOT_FOUND"
    }
}

class err500 {
    companion object {
        const val INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR"
    }
}


data class ApiErrorResponse(
    val message: String,
    val code: String,
    val path:String,
) {
    val timestamp = Date()
}
