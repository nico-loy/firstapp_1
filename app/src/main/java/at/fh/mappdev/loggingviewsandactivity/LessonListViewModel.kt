package at.fh.mappdev.loggingviewsandactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class LessonListViewModel (application: Application): AndroidViewModel(application) {
    //ViewModel implementation here

    private val loadTrigger = MutableLiveData<Unit>()
    val lessons = Transformations.switchMap(loadTrigger) {
        LessonRepository.lessonsListWithLiveData()
    }

    fun refresh(){
        loadTrigger.value = Unit
    }
}