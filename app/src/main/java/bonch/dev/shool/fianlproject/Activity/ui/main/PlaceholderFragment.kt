package bonch.dev.shool.fianlproject.Activity.ui.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User


class PlaceholderFragment : Fragment() {
    /**
     * служит для загрузки текста слайда в фрагмент
     */

    private lateinit var buttonSave: Button
    lateinit var user : User

    companion object {
        fun newInstance(message: String): PlaceholderFragment {

            val f = PlaceholderFragment()
            val bdl = Bundle(1)

            bdl.putString(EXTRA_MESSAGE, message)
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

            var admin : String = user.isAdmin



            val message = arguments!!.getString(EXTRA_MESSAGE)
            buttonSave = root!!.findViewById(bonch.dev.shool.fianlproject.R.id.buttom_save)

            var tvMessage: EditText = root!!.findViewById(R.id.section_label)
        var slaideName: EditText = root!!.findViewById(R.id.slid_name)
            tvMessage.setText(message)

        if(admin == "1"){
            tvMessage.setEnabled(true)
            slaideName.setEnabled(true)
        }else{
            buttonSave.setVisibility(View.GONE)
            tvMessage.setEnabled(false)
            slaideName.setEnabled(false)

        }



            return root
    }
}
