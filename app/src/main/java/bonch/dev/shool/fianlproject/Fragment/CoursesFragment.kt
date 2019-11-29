package bonch.dev.shool.fianlproject.Fragment

import android.os.Bundle
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


class CoursesFragment : Fragment() {

    private lateinit var coursesRecyclerView: RecyclerView
    private lateinit var coursesButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_courses, container, false)

        coursesRecyclerView = view.findViewById(R.id.recycle_courses)
        coursesRecyclerView.layoutManager = LinearLayoutManager(container!!.context)

        var adapter = CoursesAdapter()
        coursesRecyclerView.adapter = adapter

        coursesButton = view.findViewById(R.id.button_next)

        coursesButton.setOnClickListener {
            (context as MainActivity).intentCourses()
        }


        return view
    }
}
