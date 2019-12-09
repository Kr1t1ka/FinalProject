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
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException


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

            if(etPass.text.toString() !=  etPass2.text.toString()){
                Toast.makeText(
                    context, "пароли не совпадают", Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if(etPass.text.toString().count()<6){
            Toast.makeText(
                context, "пароль недостаточно сложный", Toast.LENGTH_SHORT
            ).show()
            return@setOnClickListener
            }

            val user = FirebaseAuth.getInstance().currentUser
            val newPassword = etPass.text.toString()

            user?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context, "Пароль успешно изменен", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context, "что то пошло не так, возможно вы забыли перезайти, введя " +
                                    "почту и пароль", Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        return view
    }
}


