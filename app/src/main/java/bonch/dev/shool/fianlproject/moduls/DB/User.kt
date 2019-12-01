package bonch.dev.shool.fianlproject.moduls.DB

/**
 *класс нужен чтобы определять, является ли пользователь администратором. Храним ID и Username для
 * сохранения лога дйствий(добавить)
 */
data class User( val ID: Int, val UserName: String, val isAdmin: Boolean)

