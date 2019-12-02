package bonch.dev.shool.fianlproject.moduls.DB.Courses

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

data class Slide(val ID: String, val Number: Int, val Title : String, val Body : String)
data class Course (val ID: String, val Name: String, val Description : String, val Price: Float)

class Courses{
    private  var slides : MutableList<Slide> = arrayListOf()
    private var courses : MutableList<Course> = arrayListOf()

    private  var mDatabase: FirebaseDatabase? = null
    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mAuth : FirebaseAuth

    /**
     * метод обращается к БД и возвращает коллекцию курсов
     */
    public fun readCourses(): MutableList<Course> {
        FirebaseDatabase.getInstance().getReference("Kurses") // тут должно быть courses, но что то пошло не так)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d(TAG, p0.message)
                }

                /**
                 * метод получает коллекцию курсов из БД и формирует из нее коллекцию courses
                 */
                override fun onDataChange(data: DataSnapshot) {
                    data.children.forEach { it ->
                        val id = it.key
                        val name = it.child("Name").value.toString()
                        val price = it.child("Price").value.toString()
                        val description = it.child("Description").value.toString()

                        if (id !="" && name != "" && price != "" && description != "")
                            courses.add(Course(id!!, name, description, price.toFloat()))

                    }
                }
            })


        return courses
    }

    /**
     * метод вернет коллекцию слайдов курса
     * @param idCourse id курса в БД, хранится в коллекции courses, в поле id
     */
    public fun readSlides(idCourse: String): MutableList<Slide> {
        FirebaseDatabase.getInstance().getReference("Kurses/${idCourse}/Slides") // тут должно быть courses, но что то пошло не так)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d(TAG, p0.message)
                }

                /**
                 * метод пройдет по всем слайдам в БД и сформироует из них коллекцию
                 */
                override fun onDataChange(data: DataSnapshot) {
                    data.children.forEach { it ->
                        val id = it.key
                        val body = it.child("Body").value.toString()
                        val title = it.child("Title").value.toString()
                        val number = it.child("Number").value.toString()

                        if (id !="" && body != "" && title != "" && number != "")
                            slides.add(Slide(id!!, number.toInt(), title, body))

                    }
                }

            })

        return slides
    }
}