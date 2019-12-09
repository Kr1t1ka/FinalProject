package bonch.dev.shool.fianlproject.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.Activity.StartActivity
import bonch.dev.shool.fianlproject.Fragment.change.MailFragment
import bonch.dev.shool.fianlproject.Fragment.change.NameFragment
import bonch.dev.shool.fianlproject.Fragment.change.PasswordFragment

import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var exitButton : ImageButton
    private lateinit var user: User
    private lateinit var tvName: TextView
    private lateinit var buttonChangePass: Button
    private lateinit var buttonChangeName: Button
    private lateinit var buttonChangeMail: Button
    private lateinit var passwordFragment: PasswordFragment
    private lateinit var nameFragment: NameFragment
    private lateinit var mailFragment: MailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvName = view.findViewById(R.id.user_name)
        user = (context as MainActivity).user
        exitButton = view.findViewById(R.id.button2)

        buttonChangeMail = view.findViewById(R.id.change_email)
        buttonChangeName = view.findViewById(R.id.change_name)
        buttonChangePass = view.findViewById(R.id.change_pass)

        passwordFragment = PasswordFragment()
        nameFragment = NameFragment()
        mailFragment = MailFragment()

        buttonChangePass.setOnClickListener {
            fragmentManager?.let { it1 ->
                passwordFragment.show(it1,"passwordFragment")
            }
        }

        buttonChangeName.setOnClickListener {
            fragmentManager?.let { it1 ->
                nameFragment.show(it1,"nameFragment")
            }
        }

        buttonChangeMail.setOnClickListener {
            fragmentManager?.let { it1 ->
                mailFragment.show(it1,"mailFragment")
            }
        }


        val admin : String = user.isAdmin

        tvName.text = user.UserName

        exitButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut();

            startActivity(Intent(context,  StartActivity().javaClass))
        }



        return view
    }
}


