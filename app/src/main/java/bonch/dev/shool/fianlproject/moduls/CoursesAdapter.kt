package bonch.dev.shool.fianlproject.moduls


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.Activity.MainActivity
import bonch.dev.shool.fianlproject.Fragment.StoreFragment
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import bonch.dev.shool.fianlproject.moduls.data.User


class CoursesAdapter(): RecyclerView.Adapter<CoursesAdapter.MessageHolder>() {

    var courseList : MutableList<Course> = arrayListOf()
    private lateinit var parent: ViewGroup
    private var addOdRemove: Boolean = true
    private lateinit var user:User
    private var isStore = false
    private lateinit var etName : EditText

    constructor(list: MutableList<Course>, addOdRemove: Boolean, isStore: Boolean): this(){
        courseList = list
        this.addOdRemove = addOdRemove
        this.isStore= isStore
    }

    constructor(list: MutableList<Course>, addOdRemove: Boolean, user: User, isStore: Boolean): this(){
        courseList = list
        this.addOdRemove = addOdRemove
        this.user = user
        this.isStore = isStore
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        this.parent = parent

        return MessageHolder(view)
    }

    override fun getItemCount(): Int = courseList.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind()

        etName = holder.itemView.findViewById(R.id.etName)
        val button = holder.itemView.findViewById<ImageButton>(R.id.button3)
        val button_oglav = holder.itemView.findViewById<ImageButton>(R.id.image_button)

        val bRemove = holder.itemView.findViewById<ImageButton>(R.id.ibRemove)
        val bSave = holder.itemView.findViewById<ImageButton>(R.id.ibSave)

        if(isStore && user.isAdmin == "1"){
            bSave.setOnClickListener{
                courseList[position].Name = etName.text.toString()
                (parent.context as MainActivity).saveCourse(courseList[position])
            }

            bRemove.setOnClickListener{
                (parent.context as MainActivity).removeCourse(courseList[position])
            }
        }else{
            bRemove.visibility = View.GONE
            bSave.visibility = View.GONE
            etName.isEnabled = false
        }

        button.setOnClickListener {//вешаем онклик на кнопку курса в ресайклере.
            if(addOdRemove)

                (parent.context as MainActivity).intentCourses(courseList[position])
            else
                (parent.context as MainActivity).removeCoursesUsers(courseList[position])

        }

        button_oglav.setOnClickListener {//вешаем онклик на кнопку оглавление в ресайклере.
            (parent.context as MainActivity).intentOglav(courseList[position])
        }

    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind() {}
    }
}