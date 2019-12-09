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
import com.google.firebase.auth.UserProfileChangeRequest


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
            val user = FirebaseAuth.getInstance().currentUser

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(etName.text.toString())
                .build()

            user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context, "Имя успешно изменено", Toast.LENGTH_SHORT
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
