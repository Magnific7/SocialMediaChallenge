package com.scalablescripts.socialMediaRestAPI.configs

import com.scalablescripts.socialMediaRestAPI.models.database.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}