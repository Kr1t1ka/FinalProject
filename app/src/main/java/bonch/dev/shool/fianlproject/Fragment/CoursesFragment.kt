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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CoursesFragment() : Fragment() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_courses, container, false)

        user = (context as MainActivity).user

        coursesRecyclerView = view.findViewById(R.id.recycle_courses)
        coursesRecyclerView.layoutManager = LinearLayoutManager(container!!.context)

        var adapter = CoursesAdapter()
        coursesRecyclerView.adapter = adapter

        initFirebase()

        return view
    }

    /**
     * метод переопределяет адаптер, заполняя его курсами из БД
     */
    private fun initFirebase(){
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference("Users/${user.ID}")
        val mDatabaseReferenceCourses = mFirebaseDatabase.getReference("Kurses")

        val courses = mutableListOf<Course>()
        val key = mutableListOf<String>()

        mDatabaseReference
            .addValueEventListener(object : ValueEventListener {

                /**если данные в БД меняются
                 * метод создаст список ключей курсов, добавленных пользователем
                 */
                override fun onDataChange(data: DataSnapshot) {

                    var isAdmin = data.child("isAdmin").value.toString()
                    var name = data.child("UserName").value.toString()

                    user.isAdmin = isAdmin
                    user.UserName = name

                    data.child("Courses").children.forEach { it ->
                        val id = it.child("ID").value.toString()
                        key.add(id)
                    }

                    //вешаем ивент на БД курсов
                    addEventCourses()
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.d(ContentValues.TAG, p0.message)
                }

                /**
                 * метод добавит событие на бд с курсами.
                 * заполнит список курсов и переопределит адаптер
                 */
                fun addEventCourses(){
                    mDatabaseReferenceCourses
                        .addValueEventListener(object : ValueEventListener {

                            /**если данные в БД меняются
                             * метод создаст новый список курсов юзера
                             * и переопределит адаптер
                             */
                            override fun onDataChange(data: DataSnapshot) {

                                data.children.forEach { it ->
                                    val id = it.key.toString()

                                    if (key.contains(id))
                                    {
                                        val name = it.child("Name").value.toString()
                                        val price = it.child("Price").value.toString()
                                        val description = it.child("Description").value.toString()

                                        courses.add(
                                            Course(
                                                id,
                                                name,
                                                description,
                                                price.toFloat()
                                            )
                                        )
                                    }

                                }

                                var adapter = CoursesAdapter(courses)
                                coursesRecyclerView.adapter = adapter
                            }

                            override fun onCancelled(p0: DatabaseError) {
                                Log.d(ContentValues.TAG, p0.message)
                            }

                        });
                }

            });
    }
}
