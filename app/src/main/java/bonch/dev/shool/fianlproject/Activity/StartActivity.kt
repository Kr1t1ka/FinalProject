package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {

    private lateinit var InputButton : Button
    private lateinit var RegistrationButton : Button

    private lateinit var etPassword : EditText
    private  lateinit var  etEmail : EditText

    private  var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        InputButton = findViewById(R.id.input_button)
        RegistrationButton = findViewById(R.id.regestration_button)

        etEmail = findViewById(R.id.editTextTextEmailAddress)
        etPassword = findViewById(R.id.editTextTextPassword)

        mAuth = FirebaseAuth.getInstance()

        InputButton.setOnClickListener {
            logInUser(etEmail.text.toString(), etPassword.text.toString())
        }

        RegistrationButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity().javaClass))
            finish()
        }

    }


    /**
     * Метод проверяет правильность ввода и наличие пользователя в БД. Перебрасывает на mainActivity
     * если проверки пройдены
     */
    private fun logInUser(email : String, password : String){

        if(email == "" || password == ""){
            Toast.makeText(
                this, "Не все поля заполнены", Toast.LENGTH_SHORT
            ).show()
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){

                    var id = task.result!!.user!!.uid.toString()
                    var email = task.result!!.user!!.email.toString()
                    var name = task.result!!.user!!.displayName.toString()
                    var user = User(id, name, email)

                    val intent = Intent(this,  MainActivity().javaClass)
                    intent.putExtra("User",user)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(this, "Ошиббка авторизации",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}
