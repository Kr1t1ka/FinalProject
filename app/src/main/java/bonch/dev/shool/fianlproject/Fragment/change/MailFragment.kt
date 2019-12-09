package bonch.dev.shool.fianlproject.Fragment.change

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

import bonch.dev.shool.fianlproject.R
import com.google.firebase.auth.FirebaseAuth


class MailFragment : DialogFragment() {

    lateinit var button: Button
    lateinit var etMail: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_change_email, container, false)
        button = view.findViewById(R.id.add)
        etMail = view.findViewById(R.id.etMail)

        button.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser

            user?.updateEmail(etMail.text.toString())
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context, "Почта успешно изменена", Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context, "что то пошло не так", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }

        return view
    }
}
