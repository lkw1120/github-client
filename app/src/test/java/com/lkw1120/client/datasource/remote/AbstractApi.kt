package com.lkw1120.client.datasource.remote

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.StandardCharsets

abstract class AbstractApi<T> {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun startServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    fun enqueueSuccessResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    fun enqueueErrorResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    fun enqueueFailureResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }

    fun createService(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(clazz)
    }
}