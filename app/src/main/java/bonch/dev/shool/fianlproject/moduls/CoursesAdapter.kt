package bonch.dev.shool.fianlproject.moduls

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.data.Course
import java.util.*


data class Message(val ID: Int, val messageText: String?, val sentDate: Date, val sentUser: Boolean) :
    Parcelable {
    @SuppressLint("NewApi")
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        SimpleDateFormat("dd-MM-yyyy").parse(parcel.readString()),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(messageText)
        parcel.writeByte(if (sentUser) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }
}

class  CoursesLab() {
    val coursesList: MutableList<Course>

    init {
        coursesList = mutableListOf()
        for(i in 0..10){
            val message = Course(
                "",
                "",
                "",
                1.0.toFloat()
            )
            coursesList.add(message)
        }
    }
}

class CoursesAdapter(): RecyclerView.Adapter<CoursesAdapter.MessageHolder>() {

    var messageList = CoursesLab().coursesList

    constructor(list: MutableList<Course>): this(){
        messageList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {


        val view=if(viewType==1)
            LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.courses_item, parent, false)

        return MessageHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(position)

        
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(position: Int){


        }

    }
}