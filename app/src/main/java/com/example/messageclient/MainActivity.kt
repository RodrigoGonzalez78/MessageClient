package com.example.messageclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.messageclient.ui.theme.MessageClientTheme
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageClientTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   SocketApp()
                }
            }
        }
    }
}


@Composable
fun SocketApp() {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.ui.layout.VerticalArrangement.Center
    ) {
        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Ingrese su mensaje") },
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { sendMessageToServer(message) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Enviar")
        }
    }
}



private fun sendMessageToServer(message: String) {
    val serverAddress = "192.168.0.1" // Reemplazar con la dirección IP del servidor
    val serverPort = 12345 // Reemplazar con el puerto del servidor

    try {
        val socket = Socket(serverAddress, serverPort)

        val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        writer.write(message)
        writer.flush()

        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val response = reader.readLine()

        println("Respuesta del servidor: $response")

        socket.close()
    } catch (e: Exception) {
        e.printStackTrace()
        println("Error al conectar con el servidor")
    }
}