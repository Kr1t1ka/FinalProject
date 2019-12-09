package bonch.dev.shool.fianlproject.Fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.CoursesAdapter
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.*

class StoreFragment : Fragment() {

    private lateinit var storeRecyclerView: RecyclerView
    private lateinit var bAddCourse: Button
    private lateinit var user:User
    //получаем точку входа для базы данных
    private val mFirebaseDatabase = FirebaseDatabase.getInstance()
    //получаем ссылку для работы с базой данных
    private lateinit var mDatabaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)

        user = (context as MainActivity).user
        bAddCourse = view.findViewById(R.id.bAddCourse)
        storeRecyclerView = view.findViewById(R.id.recycle_store)
        storeRecyclerView.layoutManager = LinearLayoutManager(container!!.context)
        mDatabaseReference = mFirebaseDatabase.getReference("Kurses")

        var adapter = CoursesAdapter()
        storeRecyclerView.adapter = adapter

        addEventCourses()

        bAddCourse.setOnClickListener {
            addCourse()
        }

        return view
    }

    private fun addCourse(){
        mDatabaseReference.push().setValue(Course("", "", "", 0))
    }


    private fun addEventCourses(){

        val courses = mutableListOf<Course>()

        mDatabaseReference.addValueEventListener(object : ValueEventListener {

            /**если данные в БД меняются
             * метод создаст новый список курсов
             * и переопределит адаптер
             */
            override fun onDataChange(data: DataSnapshot) {
                courses.clear()
                data.children.forEach { it ->
                    val id = it.key.toString()


                    val name = it.child("name").value.toString()
                    val price = it.child("price").value.toString()
                    val description = it.child("description").value.toString()

                    courses.add(
                        Course(
                            id,
                            name,
                            description,
                             price.toInt()
                        )
                    )


                }

                var adapter = CoursesAdapter(courses, true,
                    user, true)
                storeRecyclerView.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(ContentValues.TAG, p0.message)
            }

        });
    }
}
