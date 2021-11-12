@file:Suppress("DEPRECATION")

package com.align.domain.connectivity

import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi

@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
sealed class NetworkState(val hasInternet: Boolean) {
    object NotConnectedState : NetworkState(false)

    sealed class ConnectedState(hasInternet: Boolean) : NetworkState(hasInternet) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        data class Connected(val capabilities: NetworkCapabilities) : ConnectedState(
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        )

        @Suppress("DEPRECATION")
        data class ConnectedLegacy(val networkInfo: NetworkInfo) : ConnectedState(
            networkInfo.isConnectedOrConnecting
        )
    }
}
