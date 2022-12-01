package at.fh.mappdev.loggingviewsandactivity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonNoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //means that, if we insert a lessonNote with an id that already exists in the database the existing one will be replaced.
    fun insert(lessonNote: LessonNote)

    @Query("SELECT * FROM LessonNote where id = :id")
    fun findNoteById(id: String):LessonNote

    @Query("SELECT * FROM LessonNote where id = :id")
    fun selectWithLiveData(id: String): LiveData<LessonNote?>

}