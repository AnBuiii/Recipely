package com.example.ingredient_detect.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Composable
fun rememberPermissionState(
    permission: String,
    onPermissionResult: (Boolean) -> Unit = {}
): PermissionState {
    return rememberMutablePermissionState(permission, onPermissionResult)
}

@Stable
interface PermissionState {
    val permission: String
    val status: PermissionStatus
    fun launchPermissionRequest(): Unit
}
