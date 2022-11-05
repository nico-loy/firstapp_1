package at.fh.mappdev.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonListActivity : AppCompatActivity() {
    val lessonAdapter = LessonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        lessonAdapter.updateList(LessonRepository.lessonsList())
        val recyclerView = findViewById<RecyclerView>(R.id.lesson_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter
    }
}