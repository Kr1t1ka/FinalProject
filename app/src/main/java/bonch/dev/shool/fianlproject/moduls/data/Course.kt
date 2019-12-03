package bonch.dev.shool.fianlproject.moduls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slide(val ID: String,
                 val Number: Int,
                 val Title : String,
                 val Body : String): Parcelable

@Parcelize
data class Course (val ID: String,
                   val Name: String,
                   val Description : String,
                   val Price: Float) : Parcelable

