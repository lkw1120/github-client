package com.lkw1120.client.datasource

import com.lkw1120.client.datasource.remote.ApiService
import javax.inject.Inject

interface RemoteDataSource {

}
class RemoteDataSourceImpl @Inject constructor(
    private  val apiService: ApiService
): RemoteDataSource {

}