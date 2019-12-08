package bonch.dev.shool.fianlproject.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.Activity.StartActivity

import bonch.dev.shool.fianlproject.R
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var exitButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        exitButton = view.findViewById(R.id.button2)
        exitButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut();

            startActivity(Intent(context,  StartActivity().javaClass))
        }

        /**
         * TODO: из класса user в тул бар транслировать имя пользователя
         * и еще доделать профиль надо
         */

        return view
    }
}


