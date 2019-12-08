package bonch.dev.shool.fianlproject.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.Activity.StartActivity

import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var exitButton : ImageButton
    private lateinit var user: User
    private lateinit var addCurses: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        user = (context as MainActivity).user
        exitButton = view.findViewById(R.id.button2)
        addCurses = view.findViewById(R.id.add_cuses)
        val admin : String = user.isAdmin
        if(admin == "1"){
            addCurses.setVisibility(View.VISIBLE)
        }else{
            addCurses.setVisibility(View.GONE)
        }
        exitButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut();

            startActivity(Intent(context,  StartActivity().javaClass))
        }

        /**
         * TODO: из класса user в тул бар транслировать имя пользователя
         * и еще доделать профиль надо
         *
         */

        return view
    }
}


