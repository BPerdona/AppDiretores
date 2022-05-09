package br.com.bruno.filmlist

import br.com.bruno.directorlist.DirectorViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bruno.entities.Film

class FilmListViewModel() : ViewModel() {

    val listFilm: MutableLiveData<List<Film>> = MutableLiveData()

    fun insertFilm(film: Film){
        val list: MutableList<Film> = listFilm.value?.toMutableList() ?: return
        list.add(film)
        listFilm.value = list
    }

    fun updateFilm(updatedFilm: Film){
        var position = -1
        listFilm.value?.forEachIndexed {  index, film ->
            if(updatedFilm.id == film.id)
                position=index
        }
        val list: MutableList<Film> = listFilm.value?.toMutableList() ?: return
        list.removeAt(position)
        list.add(position, updatedFilm)
        listFilm.value = list
    }

    fun removeFilm(id: Int){
        var position = -1
        listFilm.value?.forEachIndexed {  index, film ->
            if(id == film.id)
                position=index
        }
        val list: MutableList<Film> = listFilm.value?.toMutableList() ?: return
        list.removeAt(position)
        listFilm.value = list
    }

    fun getListFilm() : List<Film>{
        val list: MutableList<Film> = listFilm.value?.toMutableList() ?: return listOf()
        return list
    }
}

