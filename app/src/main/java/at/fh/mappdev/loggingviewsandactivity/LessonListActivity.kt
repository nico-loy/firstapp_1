package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi

class LessonListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val EXTRA_LESSON_NAME = "LESSON_NAME_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    val lessonAdapter = LessonAdapter() {
        val intent = Intent(this, LessonRatingActivity::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(intent, ADD_OR_EDIT_RATING_REQUEST)
    }

   private val viewModel: LessonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        viewModel.lessons.observe(this) { when (it) {
            is NetworkResult.NetworkSuccess -> {
                lessonAdapter.updateList(it.value)
            }
            is NetworkResult.NetworkError -> {
                Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                finish() }
            is NetworkResult.NetworkLoading -> {
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show() }
        } }
        viewModel.refresh()

        val recyclerView = findViewById<RecyclerView>(R.id.lesson_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter
        parseJson()
        SleepyAsyncTask().execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == RESULT_OK) {
            //lessonAdapter.updateList(LessonRepository.lessonsList())
            LessonRepository.lessonsList(
                success = {
                    // handle success
                    lessonAdapter.updateList(it)
                },
                error = {
                    // handle error
                    Toast.makeText(this, "Error loading lessons", Toast.LENGTH_LONG).show()
                }
            )

        } else {
            Toast.makeText(
                applicationContext,
                "Rating not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun parseJson(){
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
        val json = jsonAdapter.fromJson("""
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Peter Salhofer"
                    }
                ],
                "ratings": [],
                "imageUrl": ""
            }
        """)
        Log.v("JSON", json.toString())
    }
}