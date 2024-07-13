package com.example.messageclient.domain

import com.example.messageclient.data.models.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.util.concurrent.TimeUnit

class WebSocketClient(private val url: String) {
    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(3, TimeUnit.SECONDS)
        .build()

    private lateinit var webSocket: WebSocket

    fun connect() {
        val request: Request = Request.Builder()
            .url(url)
            .build()

        webSocket = client.newWebSocket(request, MyWebSocketListener())
    }

    fun close() {
        webSocket.close(1000, "Goodbye!")
    }

    fun send(message: Message) {
        val jsonMessage = Json.encodeToString(message)
        webSocket.send(jsonMessage)
    }
}