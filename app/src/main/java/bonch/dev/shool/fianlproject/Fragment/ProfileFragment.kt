package bonch.dev.shool.fianlproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import bonch.dev.shool.fianlproject.R




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
            /**
             * сдесь должен быть выход из акаунат
             */
        }

        /**
         * TODO: из класса user в тул бар транслировать имя пользователя
         * и еще доделать профиль надо
         */

        return view
    }
}


