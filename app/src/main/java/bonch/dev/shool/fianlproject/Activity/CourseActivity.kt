package bonch.dev.shool.fianlproject.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import bonch.dev.shool.fianlproject.Activity.ui.main.PlaceholderFragment
import bonch.dev.shool.fianlproject.Activity.ui.main.SectionsPagerAdapter
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Slide
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.*

class CourseActivity : AppCompatActivity() {

    private lateinit var viewpager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var course: Course
    private lateinit var buttonSlaidPlus: Button
    private lateinit var title: EditText
    public lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course_activity)

        tabs = findViewById(R.id.tabs)
        viewpager = findViewById(R.id.view_pager)
        title = findViewById(R.id.title)
        course = intent.getParcelableExtra("Course")
        buttonSlaidPlus = findViewById(R.id.plus_slaid)
        user = intent.getParcelableExtra("User")

        var admin : String = user.isAdmin

        if(admin == "DataSnapshot { key = isAdmin, value = 0 }"){
            buttonSlaidPlus.setVisibility(View.GONE)
            title.setEnabled(false)
            /**
             * тут еще должено быть переключение TextEdit на возможность редактирования
             * по умолчанию должна стоять невозможность редактирования
             */
        }

        /**
          тут сверху
          Просто проверка на админа, проходишь ее и у тебя есть заветная кнопка, она правда ничего не делает.
         Но это только пока...
         (kritika)
         теперь еще можно поменять заголовок с finalProgect на что то другое
         админом быть круто!
         */

        buttonSlaidPlus.setOnClickListener {
            /**
             * тут по нажатию книпки долен появлятся новый слайд курса
             */
        }



        addEventCourses(course.ID)
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