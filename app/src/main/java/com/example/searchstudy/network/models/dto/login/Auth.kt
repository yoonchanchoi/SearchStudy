package com.example.searchstudy.network.models.dto.login

data class Auth(
    val user: User,
    val token: String,
    val refreshToken: String,
    val type: String = "Bearer",
    val isValidateJwtToken: Boolean = true,
    val isWrongPassword: Boolean = false,
    val isResignUser: Boolean = false
)


