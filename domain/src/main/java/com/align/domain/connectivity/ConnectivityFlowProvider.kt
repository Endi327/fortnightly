package com.align.domain.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityFlowProvider {
    fun watchConnectivity(): Flow<NetworkState>
}
