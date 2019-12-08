package bonch.dev.shool.fianlproject.Activity.ui.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import bonch.dev.shool.fianlproject.Activity.CourseActivity
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Slide
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.database.FirebaseDatabase


class PlaceholderFragment : Fragment() {
    /**
     * служит для загрузки текста слайда в фрагмент
     */

    private lateinit var buttonSave: Button

    companion object {
        fun newInstance(slide: Slide): PlaceholderFragment {

            val f = PlaceholderFragment()
            val bdl = Bundle(1)

            bdl.putParcelable(EXTRA_MESSAGE, slide)
            f.setArguments(bdl)

            return f

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root: View? = inflater.inflate(R.layout.course_fragment, container, false)

        var admin: String = (context as CourseActivity).user.isAdmin


        val slide = arguments!!.getParcelable<Slide>(EXTRA_MESSAGE)
        buttonSave = root!!.findViewById(bonch.dev.shool.fianlproject.R.id.buttom_save)

        val tvMessage: EditText = root!!.findViewById(R.id.section_label)
        val slaideName: EditText = root!!.findViewById(R.id.slid_name)
        tvMessage.setText(slide!!.Body)
        slaideName.setText(slide!!.Title)

        if (admin == "1") {
            tvMessage.setEnabled(true)
            slaideName.setEnabled(true)

            buttonSave.setOnClickListener {
                val course = (context as CourseActivity).course

                val mFirebaseDatabase = FirebaseDatabase.getInstance()
                val mDatabaseReference = mFirebaseDatabase.getReference("Kurses/${course.ID}/Slides/${slide.ID}")

                mDatabaseReference.child("/body").setValue(tvMessage.text.toString())
                mDatabaseReference.child("/title").setValue(slaideName.text.toString())
            }
        } else {
            buttonSave.setVisibility(View.GONE)
            tvMessage.setEnabled(false)
            slaideName.setEnabled(false)

        }



        return root
    }
}
