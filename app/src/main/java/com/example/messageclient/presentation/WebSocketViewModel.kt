package com.example.messageclient.presentation

import androidx.lifecycle.ViewModel
import com.example.messageclient.data.models.Message
import com.example.messageclient.domain.WebSocketClient

class WebSocketViewModel : ViewModel() {
    private var webSocketClient = WebSocketClient("ws://your_ip_address:your_port")

    fun connect(ip:String, port:String) {
        webSocketClient= WebSocketClient("ws://$ip:$port")
        webSocketClient.connect()
    }

    fun close() {
        webSocketClient.close()
    }

    fun send(message: Message) {
        webSocketClient.send(message)
    }
}