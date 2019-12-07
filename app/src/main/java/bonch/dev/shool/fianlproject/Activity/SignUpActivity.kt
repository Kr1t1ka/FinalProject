package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var RegestrtionButton : Button
    private lateinit var tvName : EditText
    private lateinit var tvPassword : EditText
    private lateinit var tvConfirmationPassword : EditText
    private lateinit var tvEmail : EditText

    private  var mDatabase: FirebaseDatabase? = null
    var mDatabaseReference: DatabaseReference? = null
    var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        RegestrtionButton = findViewById(R.id.button)

        initialize()
    }

    /**
     * инициализируем переменные для подключения к БД
     */
    private fun initialize(){
        val name = findViewById<EditText>(R.id.editTextTextPersonName)
        val password = findViewById<EditText>(R.id.editTextTextPassword2)
        val confirmationPassword = findViewById<EditText>(R.id.editTextTextPassword3)
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress2)



        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()

        RegestrtionButton.setOnClickListener{

            createNewUser(name.text.toString(), email.text.toString(), password.text.toString(),
                confirmationPassword.text.toString())


        }
    }


    /**
     * метод создает нового пользователя и отправляет в БД его данные(имя, пароль, статус, почту)
     * в случае успеха перекидывает на новую форму.
     * здесь же проверяется правильность заполнения полей. В случае ошибки выводятся тосты с
     * сообщением
     */
    private fun createNewUser(name : String, email : String, password : String,
                               confirmationPassword : String ) {
        if (name =="" || email =="" || password == "" || confirmationPassword == "")
        {
            Toast.makeText(
                this, "Не все поля заполнены", Toast.LENGTH_SHORT
            ).show()
            return
        }
        else if (!(email.contains("@")))
        {
            Toast.makeText(
                this, "Не верно указана почта", Toast.LENGTH_SHORT
            ).show()
            return
        }
        else if (password != confirmationPassword){
            Toast.makeText(
                this, "Пароли не совпадают", Toast.LENGTH_SHORT
            ).show()
            return
        }else if (password.length < 6){
            Toast.makeText(
                this, "Пароль меньше 6 символов", Toast.LENGTH_SHORT
            ).show()
            return
        }


        mAuth!!
            .createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful){
                    val userId = mAuth!!.currentUser!!.uid
                    val currentUserDb = mDatabaseReference!!.child((userId))

                    currentUserDb.child("Name").setValue(name)
                    currentUserDb.child("Password").setValue(password)
                    currentUserDb.child("Email").setValue(email)
                    currentUserDb.child("isAdmin").setValue(0)

                    var id = task.result!!.user!!.uid.toString()
                    var email = task.result!!.user!!.email.toString()
                    var name = task.result!!.user!!.displayName.toString()
                    var user = User(id, name, email,"0")

                    val intent = Intent(this,  MainActivity().javaClass)
                    intent.putExtra("UserName",user)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(
                        this, "Ошибка при регистрации", Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}
