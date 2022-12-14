package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels

class LessonNoteActivity : AppCompatActivity() {

    private val viewModel: LessonNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)
        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID).toString()
        val lessonName = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_NAME).toString()

        //In the text view show the text of the lesson note
        findViewById<TextView>(R.id.lesson_name).text = lessonName
        viewModel.findLessonNoteById(lessonId)
        viewModel.note.observe(this){
            findViewById<TextView>(R.id.lesson_note_text_view).text = it?.text
        }
        //findViewById<EditText>(R.id.lesson_note_editTextTextMultiLine).setText(LessonRepository.findLessonNoteById(this,lessonId)?.text ?:"")

        findViewById<TextView>(R.id.save_note_button).setOnClickListener {
            //add the note to the Repository
            LessonRepository.addLessonNote(this,LessonNote(lessonId, lessonName, findViewById<TextView>(R.id.lesson_note_editTextTextMultiLine).text.toString()))
           // findViewById<EditText>(R.id.lesson_note_editTextTextMultiLine).setText(LessonRepository.findLessonNoteById(this,lessonId)?.text)
            }
        }
}