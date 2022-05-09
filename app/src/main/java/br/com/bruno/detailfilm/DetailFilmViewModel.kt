package br.com.bruno.detailfilm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bruno.entities.Film

class DetailFilmViewModel : ViewModel(){

    private val _id : MutableLiveData<Int> = MutableLiveData(9)
    val title: MutableLiveData<String> = MutableLiveData("")
    val genre: MutableLiveData<String> = MutableLiveData("")
    val duration: MutableLiveData<String> = MutableLiveData("")
    val synopsis: MutableLiveData<String> = MutableLiveData("")
    val watched: MutableLiveData<Boolean> = MutableLiveData(false)

    fun insertFilm(
        onInsertFilm: (Film) -> Unit
    ){
        val newFilm = Film(
            _id.value ?: return,
            title.value ?: return,
            genre.value ?: return,
            duration.value ?: return,
            synopsis.value ?: return,
            watched.value ?: return,
        )
        onInsertFilm(newFilm)
        var auxId: Int = _id.value ?: return
        auxId++
        _id.value = auxId
        title.value = ""
        genre.value = ""
        duration.value = ""
        synopsis.value = ""
        watched.value = false
    }

    fun updateFilm(
        id: Int,
        onUpdateFilm: (Film) -> Unit
    ){
        val film = Film(
            id,
            title.value ?: return,
            genre.value ?: return,
            duration.value ?: return,
            synopsis.value ?: return,
            watched.value ?: return,
        )
        onUpdateFilm(film)
    }
}