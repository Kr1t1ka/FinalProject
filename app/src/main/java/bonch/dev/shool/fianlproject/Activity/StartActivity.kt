package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User
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

        autoLogin()

        InputButton = findViewById(R.id.input_button) // кнопка вход
        RegistrationButton = findViewById(R.id.regestration_button) // кнопка регистрации
        etEmail = findViewById(R.id.editTextTextEmailAddress) // почта
        etPassword = findViewById(R.id.editTextTextPassword) // пароль

        InputButton.setOnClickListener {
            logInUser(etEmail.text.toString(), etPassword.text.toString())
        }

        RegistrationButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity().javaClass))
            finish()
        }

    }

    /**
     * метод выполнит авторизацию, если бзер раньше входил в прилоржение
     */
    fun autoLogin(){
        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.getCurrentUser() != null) {
            val id =mAuth!!.getCurrentUser()!!.uid.toString()
            val email = mAuth!!.getCurrentUser()!!.email.toString()
            val name = mAuth!!.getCurrentUser()!!.displayName.toString()
            val user = User(id, name, email, "0")

            updateUI(user)
        }
    }

    /**
     * метод вызывает main view и передает на него юзера
     */
    fun updateUI(user : User){
        val intent = Intent(this,  MainActivity().javaClass)
        intent.putExtra("User",user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
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

                    val id = task.result!!.user!!.uid.toString()
                    val email = task.result!!.user!!.email.toString()
                    val name = task.result!!.user!!.displayName.toString()
                    val user = User(id, name, email, "0")

                    updateUI(user)

                }
                else{
                    Toast.makeText(this, "Ошибка авторизации",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
