package at.fh.mappdev.loggingviewsandactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files.size

class LessonAdapter: RecyclerView.Adapter<LessonViewHolder>() {

    private var lessonList = listOf<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val lessonItemView = inflater.inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(lessonItemView)
    }

    override fun getItemCount(): Int {

            return lessonList.size

        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class LessonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(lesson: Lesson) {

    }
}