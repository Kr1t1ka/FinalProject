package bonch.dev.shool.fianlproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.CoursesAdapter

class StoreFragment : Fragment() {

    private lateinit var storeRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)

        storeRecyclerView = view.findViewById(R.id.recycle_store)
        storeRecyclerView.layoutManager = LinearLayoutManager(container!!.context)

        var adapter = CoursesAdapter()
        storeRecyclerView.adapter = adapter

        return view
    }
}
