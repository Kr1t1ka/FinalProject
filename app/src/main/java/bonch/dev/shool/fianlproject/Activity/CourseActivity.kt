package bonch.dev.shool.fianlproject.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import bonch.dev.shool.fianlproject.Activity.ui.main.PlaceholderFragment
import bonch.dev.shool.fianlproject.Activity.ui.main.SectionsPagerAdapter
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Slide
import bonch.dev.shool.fianlproject.moduls.data.Theme
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.*

class CourseActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager
    private lateinit var tabs: TabLayout
    lateinit var course: Course
    lateinit var theme: Theme
    private lateinit var buttonSlaidPlus: Button
    private lateinit var buttonSave: Button
    lateinit var title: EditText
    lateinit var user : User

    private var slideList : MutableList<Slide> = arrayListOf()

    val mFirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var mDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)

        tabs = findViewById(R.id.tabs)
        viewpager = findViewById(R.id.view_pager)
        title = findViewById(R.id.title)
        course = intent.getParcelableExtra("Course")!!
        theme = intent.getParcelableExtra("Theme")!!
        buttonSlaidPlus = findViewById(R.id.plus_slaid)
        user = intent.getParcelableExtra("User")!!

        mDatabaseReference =  mFirebaseDatabase.getReference("Kurses/" +
                "${course.ID}/theme/${theme.ID}/Slides")

        title.setText(theme.Title)

        val admin : String = user.isAdmin

        if(admin == "1"){
            title.isEnabled = true

        }else{
            buttonSlaidPlus.visibility = View.GONE
            title.isEnabled = false
        }

        buttonSlaidPlus.setOnClickListener {
            addSlideBd()
        }


        addEventCourses(course.ID)
    }


    /**
     * функция заполняет список слайдов, создает фрагменты и устанавливает адаптер
     */
    private fun setupViewPager(slideList : MutableList<Slide>) {

        val adapter = SectionsPagerAdapter(supportFragmentManager)

        for (slide in slideList){
            adapter.addFragment(PlaceholderFragment.newInstance(slide), slide.Title)
        }

        viewpager.adapter = adapter

        tabs.setupWithViewPager(viewpager)

    }

    private fun addSlideBd(){
        val id = mDatabaseReference.push().setValue(Slide("", slideList.size, "", ""))
        Log.d(ContentValues.TAG, id.toString())

    }

    /**
     * функция устанавливает событие на бд курсов и создает список курсов
     */
    private fun addEventCourses(courseID: String){

        mDatabaseReference.addValueEventListener(object : ValueEventListener {

            /**если данные в БД меняются
             * метод создаст новый список
             */
            override fun onDataChange(data: DataSnapshot) {
                slideList.clear()

                data.children.forEach { it ->
                    val id = it.key.toString()

                    val number = it.child("number").value.toString()
                    val body = it.child("body").value.toString()
                    val title = it.child("title").value.toString()

                    if(number != null && body != null && title != null)
                        slideList.add(Slide(id, number.toInt(), title, body))
                }


                setupViewPager(slideList)
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d(ContentValues.TAG, p0.message)
            }

        });
    }

    override fun onBackPressed() {
        var intent = Intent(this, OglavlenieActivity().javaClass)
        intent.putExtra("User",user)
        intent.putExtra("Theme",theme)
        intent.putExtra("Course",course)
        startActivity(intent)
        finish()

    }

}