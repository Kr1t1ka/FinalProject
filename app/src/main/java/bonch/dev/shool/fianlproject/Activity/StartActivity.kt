package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import bonch.dev.shool.fianlproject.R
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {

    private lateinit var InputButton : ImageButton
    private lateinit var RegistrationButton : Button

    private lateinit var etPassword : EditText
    private  lateinit var  etEmail : EditText

    private  var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        InputButton = findViewById(R.id.input_button) // кнопка вход
        RegistrationButton = findViewById(R.id.regestration_button) // кнопка регистрации

        etEmail = findViewById(R.id.editTextTextEmailAddress) // почта
        etPassword = findViewById(R.id.editTextTextPassword) // пароль

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
                    val intent = Intent(this,  MainActivity().javaClass)
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
