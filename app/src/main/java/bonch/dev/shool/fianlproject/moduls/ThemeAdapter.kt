package bonch.dev.shool.fianlproject.moduls

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.Activity.CourseActivity
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.Activity.OglavlenieActivity
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.Theme


class ThemeAdapter(): RecyclerView.Adapter<ThemeAdapter.MessageHolder>() {

    var themeList : MutableList<Theme> = arrayListOf()
    private lateinit var parent: ViewGroup

    constructor(list: MutableList<Theme>): this(){
        themeList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        this.parent = parent

        return MessageHolder(view)
    }

    override fun getItemCount(): Int = themeList.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind()


        val etName = holder.itemView.findViewById<EditText>(R.id.etName)
        etName.setText(themeList[position].Title)
        val button = holder.itemView.findViewById<ImageButton>(R.id.button3)
        val bRemove = holder.itemView.findViewById<ImageButton>(R.id.ibRemove)
        val bSave = holder.itemView.findViewById<ImageButton>(R.id.ibSave)
        val button_oglav = holder.itemView.findViewById<ImageButton>(R.id.image_button)
        val textview = holder.itemView.findViewById<TextView>(R.id.textView3)
        val textview1 = holder.itemView.findViewById<TextView>(R.id.textView5)
        val textview2 = holder.itemView.findViewById<TextView>(R.id.textView6)




        textview.text = "Перейти к главе"


        if((parent.context as OglavlenieActivity).user.isAdmin == "1") {
            button.setOnClickListener {
                (parent.context as OglavlenieActivity).deleteTheme(themeList[position])
            }
            bRemove.setOnClickListener {
                (parent.context as OglavlenieActivity).deleteTheme(themeList[position])
            }
            bSave.setOnClickListener {
                themeList[position].Title = etName.text.toString()
                (parent.context as OglavlenieActivity).saveTheme(themeList[position])
            }
        }else{
            button.visibility = View.GONE
            bRemove.visibility = View.GONE
            bSave.visibility = View.GONE
            etName.isEnabled = false
            textview1.visibility = View.GONE
            textview2.visibility = View.GONE
        }

        button_oglav.setOnClickListener {
            (parent.context as OglavlenieActivity).intentCourses(themeList[position])

        }

    }


    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind() {}
    }
}