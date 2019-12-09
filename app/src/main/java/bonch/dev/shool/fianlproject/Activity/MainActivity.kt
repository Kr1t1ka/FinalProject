package bonch.dev.shool.fianlproject.Activity

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Theme
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    public lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        user = intent.getParcelableExtra("User")!!

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration(
            setOf(
                R.id.navigation_courses,
                R.id.navigation_store,
                R.id.navigation_profile,
                R.id.navigation_ficha
            )
        )

        navView.setupWithNavController(navController)
    }


    fun intentCourses(course: Course) {
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference(
            "Users/" +
                    "${user.ID}/Courses"
        )

        mDatabaseReference
            .addListenerForSingleValueEvent(object : ValueEventListener {
                /**
                 * по значению ищем добавляемый курс в списке добавленных. если находим, вылетаем
                 * из метода
                 */
                override fun onDataChange(data: DataSnapshot) {

                    data.children.forEach { it ->
                        if (it.value.toString() == course.ID)
                            return
                    }
                    mDatabaseReference.push().setValue(course.ID)
                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.d(ContentValues.TAG, p0.message)
                }
            })
    }

    fun saveCourse(course: Course) {
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference("Kurses")

        mDatabaseReference.child(course.ID).child("name").setValue(course.Name)
    }

    fun removeCourse(course: Course) {
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference("Kurses")

        mDatabaseReference.child(course.ID).removeValue()
    }

    fun removeCoursesUsers(course: Course) {
        //получаем точку входа для базы данных
        val mFirebaseDatabase = FirebaseDatabase.getInstance()
        //получаем ссылку для работы с базой данных
        val mDatabaseReference = mFirebaseDatabase.getReference(
            "Users/" +
                    "${user.ID}/Courses"
        )

        mDatabaseReference
            .addListenerForSingleValueEvent(object : ValueEventListener {
                /**
                 * по значению ищем курс для удаления... по моему это плохо
                 */
                override fun onDataChange(data: DataSnapshot) {

                    data.children.forEach { it ->
                        if (it.value.toString() == course.ID)
                            mDatabaseReference.child(it.key.toString()).removeValue()
                    }

                }

                override fun onCancelled(p0: DatabaseError) {
                    Log.d(ContentValues.TAG, p0.message)
                }
            })
    }

    fun intentOglav(course: Course) {
        val intent = Intent(this, OglavlenieActivity().javaClass)
        intent.putExtra("Course", course)
        intent.putExtra("User", user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Exit")
            .setMessage("Вы действительно хотите выйти?")
            .setPositiveButton("Да",
                DialogInterface.OnClickListener { dialog, which ->
                    finishAffinity()
                    finish()
                }
            ).setNegativeButton("Нет", null).show()
    }

}

