package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import bonch.dev.shool.fianlproject.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var RegestrtionButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        RegestrtionButton = findViewById(R.id.button)

        RegestrtionButton.setOnClickListener{
            startActivity(Intent(this, MainActivity().javaClass))
            finish()
        }
    }
}
