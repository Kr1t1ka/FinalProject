package bonch.dev.shool.fianlproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import bonch.dev.shool.fianlproject.R

class CoursesActivity : AppCompatActivity() {

    private lateinit var swipeView : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.courses_activity)

        swipeView = findViewById(R.id.view_pager)
    }
}
