package id.ac.unpas.mynote.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.ac.unpas.mynote.NoteDatabase
import id.ac.unpas.mynote.models.Note
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun NoteScreen() {
    val context = LocalContext.current
    val db = remember { NoteDatabase.getInstance(context) }
    val dao = db.noteDao()
    val notes by dao.getAll().collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Tambah Catatan", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Judul") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    val note = Note(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        description = description
                    )
                    scope.launch {
                        dao.insert(note)
                        title = ""
                        description = ""
                        Toast.makeText(context, "Catatan disimpan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Judul dan deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Simpan")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        Text("Daftar Catatan", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(notes) { note ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
                    Divider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}
