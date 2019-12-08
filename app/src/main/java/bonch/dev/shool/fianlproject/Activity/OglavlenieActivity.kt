package bonch.dev.shool.fianlproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import bonch.dev.shool.fianlproject.R

class OglavlenieActivity : AppCompatActivity() {

    private lateinit var spinnerOglavlenie : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oglavlenie_activity)

    }
}
