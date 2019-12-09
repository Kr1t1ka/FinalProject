package bonch.dev.shool.fianlproject.moduls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slide(val ID: String,
                 val Number: Int,
                 val Title : String,
                 val Body : String): Parcelable

@Parcelize
data class Course(
    val ID: String,
    var Name: String,
    var Description: String,
    val Price: Int
) : Parcelable

@Parcelize
data class Theme(val ID: String,
                 var Title: String) : Parcelable

