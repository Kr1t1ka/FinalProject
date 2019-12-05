package bonch.dev.shool.fianlproject.Activity.ui.main

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bonch.dev.shool.fianlproject.R



class PlaceholderFragment : Fragment() {
    /**
     * служит для загрузки текста слайда в фрагмент
     */
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
            var root: View? = inflater.inflate(R.layout.course_fragment, container, false);

            val message = arguments!!.getString(EXTRA_MESSAGE)

            var tvMessage: TextView  = root!!.findViewById(R.id.section_label)
            tvMessage.text = message

            return root
    }

}
