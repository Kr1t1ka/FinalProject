package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.User

class MainActivity : AppCompatActivity() {

    public lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        user = intent.getParcelableExtra("User")

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_courses, R.id.navigation_store, R.id.navigation_profile, R.id.navigation_ficha
            )
        )

        navView.setupWithNavController(navController)
    }


    fun intentCourses(course : Course) {
        val intent = Intent(this, CourseActivity().javaClass)
        intent.putExtra("Course", course)
        intent.putExtra("User", user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        startActivity(intent)
        finish()
    }
}
