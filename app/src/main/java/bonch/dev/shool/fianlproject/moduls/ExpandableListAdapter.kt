package bonch.dev.shool.fianlproject.moduls

import android.R
import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class MyListActivity : ListActivity() {
    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        val values = arrayOf(
            "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2"
        )

    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val item = listAdapter.getItem(position) as String
        Toast.makeText(this, "$item selected", Toast.LENGTH_LONG).show()
    }
}