package dk.easj.anbo.viewmodelmasterdetail

class Student(
    var id: Int = -1,
    val name: String,
    val address: String,
    val yearOfBirth: Int,
    val semester: Int
) {
    override fun toString(): String {
        return "$id $name";
    }
}