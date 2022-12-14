package at.fh.mappdev.loggingviewsandactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class LessonAdapter(val clickListener: (lesson: Lesson) -> Unit) : RecyclerView.Adapter<LessonViewHolder>() {

    private var lessonList = listOf<Lesson>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val lessonItemView = inflater.inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(lessonItemView,clickListener)
    }

    override fun getItemCount(): Int {

        return lessonList.count()

        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        //TODO get the correct lesson Object and pass it to bindItem
        holder.bindItem(lessonList[position])
    }

    fun updateList(newList: List<Lesson>) {
        lessonList = newList
        notifyDataSetChanged()
    }
}

class LessonViewHolder(itemView: View, val clickListener: (lesson: Lesson) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(lesson: Lesson) {
        itemView.findViewById<TextView>(R.id.item_lesson_name).text = lesson.name
        itemView.findViewById<TextView>(R.id.item_lesson_date).text = lesson.date

        itemView.setOnClickListener {
            clickListener(lesson)
        }

        itemView.findViewById<TextView>(R.id.item_lesson_topic).text = lesson.topic
        itemView.findViewById<TextView>(R.id.item_lesson_lecturers).text = lesson.lecturers.joinToString {it.name}
        itemView.findViewById<RatingBar>(R.id.item_lesson_avg_rating_bar).rating = lesson.ratingAverage().toFloat()
        itemView.findViewById<TextView>(R.id.item_lesson_avg_rating_value).text = if (lesson.ratingAverage().isNaN()) "No Rating" else lesson.ratingAverage().toString()
        itemView.findViewById<TextView>(R.id.item_lesson_rating_count).text = "Ratings: " + lesson.ratings.count().toString()

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        Glide
            .with(itemView)
            .load(lesson.imageUrl)
            .into(imageView)
    }
}