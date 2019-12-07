package bonch.dev.shool.fianlproject.moduls


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course


class CoursesAdapter(): RecyclerView.Adapter<CoursesAdapter.MessageHolder>() {

    var courseList : MutableList<Course> = arrayListOf()
    private lateinit var parent: ViewGroup

    constructor(list: MutableList<Course>): this(){
        courseList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        this.parent = parent

        return MessageHolder(view)
    }

    override fun getItemCount(): Int = courseList.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(position)

        var button = holder.itemView.findViewById<ImageButton>(R.id.button3)

        button.setOnClickListener {//вешаем онклик на кнопку курса в ресайклере.
            (parent.context as MainActivity).intentCourses(courseList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(position: Int){

        }

    }
}