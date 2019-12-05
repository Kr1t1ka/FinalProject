package bonch.dev.shool.fianlproject.moduls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *класс нужен чтобы определять, является ли пользователь администратором. Храним ID и Username для
 * сохранения лога дйствий(добавить)
 */
@Parcelize
data class User( val ID: String,
                 var UserName: String,
                 val email: String,
                 var isAdmin: String): Parcelable

