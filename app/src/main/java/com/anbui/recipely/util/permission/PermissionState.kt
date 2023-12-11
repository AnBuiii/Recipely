package com.anbui.recipely.util.permission

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
