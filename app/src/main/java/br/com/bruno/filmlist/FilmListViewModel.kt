package br.com.bruno.filmlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bruno.entities.Film

class FilmListViewModel : ViewModel() {

    private val _listFilm: MutableLiveData<List<Film>> = MutableLiveData(
        listOf(
            Film(
                0,
                "Clube da Luta",
                "Ação",
                "1hr 30min",
                "Está é a sinopse",
                true
            ),
            Film(
                1,
                "Memento",
                "Drama",
                "1hr 56min",
                "Ele tem amninesia",
                true
            ),
            Film(
                2,
                "Star Wars",
                "Fantasia",
                "2hr 12min",
                "Batalhas no espaço",
                false
            ),
            Film(
                3,
                "A Espera de um Milagre",
                "Drama",
                "3hr 12min",
                "Um anjo injustiçado",
                true
            ),
            Film(
                4,
                "Até o ultimo Homem",
                "Guerra",
                "1hr 40min",
                "Segunda guerra mundial",
                false
            ),
            Film(
                5,
                "Django Livre",
                "Ação",
                "2hr 45min",
                "O ex-escravo Django",
                false
            ),
        )
    )

    val listFilm: LiveData<List<Film>>
        get(){
            return _listFilm
        }

    fun insertFilm(film: Film){
        val list: MutableList<Film> = _listFilm.value?.toMutableList() ?: return
        list.add(film)
        _listFilm.value = list
    }

    fun updateFilm(updatedFilm: Film){
        var position = -1
        _listFilm.value?.forEachIndexed {  index, film ->
            if(updatedFilm.id == film.id)
                position=index
        }
        val list: MutableList<Film> = _listFilm.value?.toMutableList() ?: return
        list.removeAt(position)
        list.add(position, updatedFilm)
        _listFilm.value = list
    }

    fun removeFilm(id: Int){
        var position = -1
        _listFilm.value?.forEachIndexed {  index, film ->
            if(id == film.id)
                position=index
        }
        val list: MutableList<Film> = _listFilm.value?.toMutableList() ?: return
        list.removeAt(position)
        _listFilm.value = list
    }

    fun getFilm(id: Int): Film{
        _listFilm.value?.forEach{
            if(id == it.id)
                return it
        }
        return Film(
            -1,
            "",
            "",
            "",
            "",
            false
        )
    }
}

