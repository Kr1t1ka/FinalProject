package bonch.dev.shool.fianlproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.User

class OglavlenieActivity : AppCompatActivity() {

    private lateinit var glavaRecyclerView: RecyclerView
    private lateinit var addGlava: Button
    lateinit var user : User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.oglavlenie_activity)

        glavaRecyclerView = findViewById(R.id.recycle_glav)
        addGlava = findViewById(R.id.add_glava)
        user = intent.getParcelableExtra("User")!!
        val admin : String = user.isAdmin

        if(admin == "1"){
            addGlava.setVisibility(View.VISIBLE)
        }else{
            addGlava.setVisibility(View.GONE)
        }

        addGlava.setOnClickListener {
            /**
             * нажатие добавляет главу
             */
        }

        /**
         * cardview для этого ресайклера list_item.xml
         */

    }
}
