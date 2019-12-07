package bonch.dev.shool.fianlproject.Fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import bonch.dev.shool.fianlproject.R
import bonch.dev.shool.fianlproject.moduls.networking.RetrofitFactory
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class FichaFragment : Fragment() {

    private  lateinit var tvFacts: TextView
    private  lateinit var ivCats: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ficha, container, false)

        tvFacts = view.findViewById(R.id.tvFacts)
        ivCats = view.findViewById(R.id.ivCats)

        getFact()

        return view
    }

    /**
     * функция создает корутину, которая обращается к серверу и получает случайный факт о котиках
     * и случайную картинку с котиком
     * мы же все любим котиков)
     * полученный факт сразу загружается в текствью и ImageView
     */
    private fun getFact(){
        val service = RetrofitFactory.makeRetrofitService(true)
        val servicePhoto = RetrofitFactory.makeRetrofitService(false)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.GetFacts()
            val responsePhoto = servicePhoto.GetCats()
            try {
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && responsePhoto.isSuccessful) {
                        try {
                            Glide.with(context).load(responsePhoto.body()!![0].url).into(ivCats)

                            tvFacts.text = response.body()!!.text.toString()

                        } catch (err: HttpException) {
                            Log.d(ContentValues.TAG, err.message)
                        }
                    } else {
                        Toast.makeText(
                            context, response.code().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (err: HttpException) {
                Log.d(ContentValues.TAG, err.message())
            }

        }
    }

}
