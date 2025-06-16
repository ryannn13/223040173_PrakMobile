package id.ac.unpas.mynote.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.mynote.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}
