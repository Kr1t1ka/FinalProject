package bonch.dev.shool.fianlproject.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import bonch.dev.shool.fianlproject.R

class StartActivity : AppCompatActivity() {

    private lateinit var InputButton : Button
    private lateinit var RegistrationButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        InputButton = findViewById(R.id.input_button)
        RegistrationButton = findViewById(R.id.regestration_button)

        InputButton.setOnClickListener {
            startActivity(Intent(this, MainActivity().javaClass))
            finish()
        }

        RegistrationButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity().javaClass))
            finish()
        }
    }
}
