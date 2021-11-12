package com.align.data.connectivity

import com.align.domain.connectivity.ConnectivityFlowProvider
import com.align.domain.connectivity.ConnectivityProvider
import com.align.domain.connectivity.NetworkState
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Singleton
class ConnectivityFlowImpl @Inject constructor(
    private val connectivityProvider: ConnectivityProvider
) : ConnectivityFlowProvider {

    @ExperimentalCoroutinesApi
    override fun watchConnectivity(): Flow<NetworkState> = callbackFlow {
        val listener = object : ConnectivityProvider.ConnectivityStateListener {
            override fun onStateChange(state: NetworkState) {
                sendBlocking(state)
            }
        }
        connectivityProvider.addListener(listener)

        awaitClose { connectivityProvider.removeListener(listener) }
    }
}
