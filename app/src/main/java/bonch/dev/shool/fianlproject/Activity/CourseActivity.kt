package bonch.dev.shool.fianlproject.Activity

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import bonch.dev.shool.fianlproject.Activity.ui.main.PlaceholderFragment
import bonch.dev.shool.fianlproject.Activity.ui.main.SectionsPagerAdapter
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Slide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CourseActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)

        tabs = findViewById(R.id.tabs)
        viewpager = findViewById(R.id.view_pager)
        course = intent.getParcelableExtra("Course")

        addEventCourses(course.ID)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    /**
     * функция заполняет список слайдов, создает фрагменты и устанавливает адаптер
     */
    private fun setupViewPager(slideList : MutableList<Slide>) {

        val adapter = SectionsPagerAdapter(supportFragmentManager)

        for (slide in slideList){
            adapter.addFragment(PlaceholderFragment.newInstance(slide.Body), slide.Title)
        }

        viewpager!!.adapter = adapter

        tabs!!.setupWithViewPager(viewpager)

    }

    /**
     * функция устанавливает событие на бд курсов и создает список курсов
     */
    private fun addEventCourses(courseID: String){
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference("Kurses/${courseID}/Slides")

        var slideList : MutableList<Slide> = arrayListOf()

        mDatabaseReference.addValueEventListener(object : ValueEventListener {

            /**если данные в БД меняются
             * метод создаст новый список
             */
            override fun onDataChange(data: DataSnapshot) {

                data.children.forEach { it ->
                    val id = it.key.toString()

                    val number = it.child("Number").value.toString()
                    val body = it.child("Body").value.toString()
                    val title = it.child("Title").value.toString()

                    slideList.add(Slide(id, number.toInt(), title, body))
                }


                setupViewPager(slideList)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(ContentValues.TAG, p0.message)
            }

        });
    }

}