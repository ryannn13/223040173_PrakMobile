package id.ac.unpas.mynote.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
        @PrimaryKey
        val id: String,
        val title: String,
        val description: String
)
