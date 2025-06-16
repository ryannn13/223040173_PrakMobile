package id.ac.unpas.mynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import id.ac.unpas.mynote.screens.NoteScreen
import id.ac.unpas.mynote.ui.theme.MyNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NoteScreen()
                }
            }
        }
    }
}
