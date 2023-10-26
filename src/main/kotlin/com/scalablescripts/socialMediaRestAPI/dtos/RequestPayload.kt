package com.scalablescripts.socialMediaRestAPI.dtos

/**
 * This file contains all incoming DTOs.
 * Here, [LoginDto] is a data class containing immutable class members
 */
data class LoginDto(
    val name: String,
    val password: String,
)

data class RegisterDto(
    val name: String,
    val password: String,
)
