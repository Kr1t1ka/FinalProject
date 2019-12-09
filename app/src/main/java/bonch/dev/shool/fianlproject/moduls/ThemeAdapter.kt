package bonch.dev.shool.fianlproject.moduls

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
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

        val tvName = holder.itemView.findViewById<TextView>(R.id.textView2)
        tvName.text = themeList[position].Title
        val etName = holder.itemView.findViewById<EditText>(R.id.textView2)
        val button = holder.itemView.findViewById<ImageButton>(R.id.button3)
        val bRemove = holder.itemView.findViewById<ImageButton>(R.id.ibRemove)
        val bSave = holder.itemView.findViewById<ImageButton>(R.id.ibSave)
        val button_oglav = holder.itemView.findViewById<ImageButton>(R.id.image_button)

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

        }

        button_oglav.setOnClickListener {
            (parent.context as OglavlenieActivity).intentCourses(themeList[position])

        }

    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind() {}
    }
}