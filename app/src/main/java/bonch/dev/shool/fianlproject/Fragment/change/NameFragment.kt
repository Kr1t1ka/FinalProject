package bonch.dev.shool.fianlproject.Fragment.change

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

import bonch.dev.shool.fianlproject.R


class NameFragment : DialogFragment() {

    lateinit var button: Button
    lateinit var etName: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_change_name, container, false)
        button = view.findViewById(R.id.add)
        etName = view.findViewById(R.id.etName)
        button.setOnClickListener {
            /**
             * смена имя
             */
        }

        return view
    }
}
