package com.example.messageclient.domain
import okhttp3.*



class MyWebSocketListener : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("WebSocket opened: $response")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("Receiving: $text")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
        println("Closing: $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("Error: " + t.message)
    }
}