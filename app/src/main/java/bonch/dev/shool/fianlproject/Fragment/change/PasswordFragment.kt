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


class PasswordFragment : DialogFragment() {

    lateinit var button: Button
    lateinit var etPass: EditText
    lateinit var etPass2: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.change_pass, container, false)
        button = view.findViewById(R.id.add)
        etPass =view.findViewById(R.id.etPass)
        etPass2 =view.findViewById(R.id.etPass2)

        button.setOnClickListener {
            /**
             * смена пароля
             */
        }

        return view
    }
}


