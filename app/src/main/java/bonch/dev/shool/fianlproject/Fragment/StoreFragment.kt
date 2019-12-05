package bonch.dev.shool.fianlproject.Fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.CoursesAdapter
import bonch.dev.shool.fianlproject.moduls.data.Course
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StoreFragment : Fragment() {

    private lateinit var storeRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)

        storeRecyclerView = view.findViewById(R.id.recycle_store)
        storeRecyclerView.layoutManager = LinearLayoutManager(container!!.context)

        var adapter = CoursesAdapter()
        storeRecyclerView.adapter = adapter

        addEventCourses()

        return view
    }


    private fun addEventCourses(){
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference("Kurses")
        val courses = mutableListOf<Course>()

        mDatabaseReference.addValueEventListener(object : ValueEventListener {

            /**если данные в БД меняются
             * метод создаст новый список курсов
             * и переопределит адаптер
             */
            override fun onDataChange(data: DataSnapshot) {

                data.children.forEach { it ->
                    val id = it.key.toString()


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

                var adapter = CoursesAdapter(courses)
                storeRecyclerView.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(ContentValues.TAG, p0.message)
            }

        });
    }
}
