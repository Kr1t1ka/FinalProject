package bonch.dev.shool.fianlproject.Activity.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private var _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "ва́н IV Васи́льевич, прозванный Гро́зным (впервые — в «Истории Российской» Татищева[6]), также имел имена Тит и Смарагд[7], в постриге — Иона (25 августа 1530, село Коломенское[8] под Москвой — 18 (28) марта 1584, Москва) — государь, великий князь московский и всея Руси с 1533 года, первый царь всея Руси (с 1547 года; кроме 1575—1576, когда «великим князем всея Руси» номинально был Симеон Бекбулатович).\n" +
                "\n" +
                "Старший сын великого князя московского Василия III и Елены Глинской. Номинально Иван стал правителем в 3 года. После восстания в Мос: $it"
    }



    fun setIndex(index: Int) {
        _index.value = index
    }


}