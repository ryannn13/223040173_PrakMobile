package com.example.hellocompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocompose.ui.theme.HelloComposeTheme

class FormRegistrasiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                FormRegistrasi()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormRegistrasi() {
    val context = LocalContext.current

    var nama by remember { mutableStateOf(TextFieldValue()) }
    var username by remember { mutableStateOf(TextFieldValue()) }
    var nomorTelepon by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var alamat by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputField("Nama", nama) { nama = it }
        InputField("Username", username) { username = it }
        InputField("Nomor Telepon", nomorTelepon, KeyboardType.Phone) { nomorTelepon = it }
        InputField("Email", email, KeyboardType.Email) { email = it }
        InputField("Alamat Rumah", alamat) { alamat = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Button(onClick = {
                if (nama.text.isNotBlank() && username.text.isNotBlank() && nomorTelepon.text.isNotBlank() &&
                    email.text.isNotBlank() && alamat.text.isNotBlank()
                ) {
                    Toast.makeText(context, "Halo, ${nama.text}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Semua inputan harus diisi", Toast.LENGTH_LONG).show()
                }
            }) {
                Text("Simpan", fontSize = 18.sp)
            }

            Button(onClick = {
                nama = TextFieldValue()
                username = TextFieldValue()
                nomorTelepon = TextFieldValue()
                email = TextFieldValue()
                alamat = TextFieldValue()
            }) {
                Text("Reset", fontSize = 18.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(label: String, value: TextFieldValue, keyboardType: KeyboardType = KeyboardType.Text, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFormRegistrasi() {
    HelloComposeTheme {
        FormRegistrasi()
    }
}
