package bonch.dev.shool.fianlproject.moduls.networking

import bonch.dev.shool.fianlproject.moduls.data.network.Cats
import bonch.dev.shool.fianlproject.moduls.data.network.Facts
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("/facts/random?amount=1")
    suspend fun GetFacts(): Response<Facts>

    @GET("/v1/images/search?api_key=1204fe26-368f-4251-8cb4-9b5966134e3c&mime_types=jpg,png&limit=1")
    suspend fun GetCats(): Response<List<Cats>>
}

/**
 *фабрика принимает параметр в зависимости от которого установит урл загрузки факта или фото
 */
object RetrofitFactory {

    const val BASE_URL = "https://cat-fact.herokuapp.com"
    const val BASE_URL_CATS_PHOTOS = "https://api.thecatapi.com"

    fun makeRetrofitService (isFactOrPhoto: Boolean) : RetrofitService {

        val retrofit = Retrofit.Builder()
            .baseUrl(if (isFactOrPhoto) BASE_URL else BASE_URL_CATS_PHOTOS)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

}