package dk.easj.anbo.viewmodelmasterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentsViewModel : ViewModel() {
    private var _nextId = 1
    private var _studentsList = mutableListOf(
        Student(_nextId++, "Anders", "Roskilde", 1966, 59),
        Student(_nextId++, "Benjamin", "Ringsted", 1992, 4)
    )
    private var _students = MutableLiveData<List<Student>>(_studentsList)
    val selected = MutableLiveData<Student>()

    var students: LiveData<List<Student>> = _students

    fun add(student: Student) {
        student.id = _nextId++
        _studentsList.add(student)
        _students.value = _studentsList
    }

    fun remove(id: Int) {
        _studentsList.removeAll { student -> student.id == id }
    }

    operator fun get(position: Int): Student { // [] operator overloading
        return _studentsList[position]
    }


}