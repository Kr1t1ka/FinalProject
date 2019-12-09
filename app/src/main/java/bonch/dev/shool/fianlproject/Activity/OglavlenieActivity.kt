package bonch.dev.shool.fianlproject.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.CoursesAdapter
import bonch.dev.shool.fianlproject.moduls.ThemeAdapter
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Slide
import bonch.dev.shool.fianlproject.moduls.data.Theme
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.main_activity.*

class OglavlenieActivity : AppCompatActivity() {

    private lateinit var themeRecyclerView: RecyclerView
    private lateinit var bAddTheme: Button
    lateinit var user: User
    private lateinit var course: Course

    private val mFirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var  mDatabaseReference : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oglavlenie_activity)


        user = intent.getParcelableExtra("User")!!
        course = intent.getParcelableExtra("Course")!!

        mDatabaseReference = mFirebaseDatabase.getReference(
            "Kurses/${course.ID}/theme")

        themeRecyclerView = findViewById(R.id.recycle_theme)
        bAddTheme = findViewById(R.id.bAddTheme)

        if (user.isAdmin == "1"){
            bAddTheme.setOnClickListener {
                addTheme()
            }
        }else{
            bAddTheme.visibility = View.GONE
        }

        themeRecyclerView.layoutManager = LinearLayoutManager(this)

        initFirebase()

    }

    private fun addTheme(){
        mDatabaseReference.push().setValue(Theme("", ""))

    }

    fun deleteTheme(theme: Theme){
        mDatabaseReference.child(theme.ID).removeValue()
    }

    fun saveTheme(theme: Theme){
        mDatabaseReference.child(theme.ID).child("title").setValue(theme.Title)
    }

    fun intentCourses(theme : Theme){
        val intent = Intent(this, CourseActivity().javaClass)
        intent.putExtra("Theme", theme)
        intent.putExtra("Course", course)
        intent.putExtra("User", user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)
        finish()
    }

    private fun initFirebase() {
        val theme = mutableListOf<Theme>()

        mDatabaseReference
            .addValueEventListener(object : ValueEventListener {
                /**
                 *считываем список всех глав и добавляем в ресайклер
                 */
                override fun onDataChange(data: DataSnapshot) {
                    theme.clear()
                    data.children.forEach { it ->
                        val id = it.key.toString()
                        val title = it.child("title").value.toString()

                        theme.add(Theme(id,  title))
                    }

                    var adapter = ThemeAdapter(theme)
                    themeRecyclerView.adapter = adapter
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.d(ContentValues.TAG, p0.message)
                }
            })

    }


}
