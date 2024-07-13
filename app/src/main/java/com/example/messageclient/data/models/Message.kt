package com.example.messageclient.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val clientName: String,
    val message: String,
    val time: String
)