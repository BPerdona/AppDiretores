package br.com.bruno.directorlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bruno.R
import br.com.bruno.entities.Director
import br.com.bruno.entities.Film

class DirectorViewModel : ViewModel(){

    private val _listDirector: MutableLiveData<List<Director>> = MutableLiveData(
        listOf(
            Director(
                0,
                "David Fincher",
                59,
                listOf(
                    Film(
                        0,
                        "Clube da Luta",
                        "Suspense",
                        "2hr 19min",
                        "Um homem deprimido que sofre de insônia conhece um estranho vendedor chamado Tyler Durden e se vê morando em uma casa suja depois que seu perfeito apartamento é destruído. A dupla forma um clube com regras rígidas onde homens lutam. A parceria perfeita é comprometida quando uma mulher, Marla, atrai a atenção de Tyler.",
                        true
                    ),
                    Film(
                        3,
                        "Os Reis de DogTown",
                        "Drama",
                        "1hr 47min",
                        "Na década de 1970, a seca na Califórnia tem um efeito inesperado: em Venice, surfistas começam a usar as piscinas vazias das casas para andar de skate, criando um novo esporte radical.",
                        false
                    )
                ),
                image = R.drawable.fincher
            ),
            Director(
                1,
                "Christopher Nolan",
                51,
                listOf(
                    Film(
                        1,
                        "Amnésia",
                        "Mistério",
                        "1hr 54min",
                        "Leonard está caçando o homem que estuprou e matou sua esposa. Ele tem dificuldades em encontrar o assassino pois sofre de uma forma intratável de perda de memória. Mesmo que ele possa lembrar detalhes da vida antes do acidente, Leonard não consegue lembrar o que aconteceu quinze minutos atrás, onde está indo ou a razão.",
                        true
                    ),
                    Film(
                        4,
                        "Interestelar",
                        "Ficção",
                        "2hr 49min",
                        "As reservas naturais da Terra estão chegando ao fim e um grupo de astronautas recebe a missão de verificar possíveis planetas para receberem a população mundial, possibilitando a continuação da espécie. Cooper é chamado para liderar o grupo e aceita a missão sabendo que pode nunca mais ver os filhos. Ao lado de Brand, Jenkins e Doyle, ele seguirá em busca de um novo lar.",
                        true
                    ),
                    Film(
                        5,
                        "A Origem",
                        "Ação",
                        "2hr 28min",
                        "Dom Cobb é um ladrão com a rara habilidade de roubar segredos do inconsciente, obtidos durante o estado de sono. Impedido de retornar para sua família, ele recebe a oportunidade de se redimir ao realizar uma tarefa aparentemente impossível: plantar uma ideia na mente do herdeiro de um império. Para realizar o crime perfeito, ele conta com a ajuda do parceiro Arthur, o discreto Eames e a arquiteta de sonhos Ariadne. Juntos, eles correm para que o inimigo não antecipe seus passos.",
                        false
                    ),
                ),
                image = R.drawable.christopher
            ),
            Director(
                2,
                "Quentin Tarantino",
                59,
                listOf(
                    Film(
                        6,
                        "Bastardos Inglórios",
                        "Guerra",
                        "2hr 33min",
                        "Durante a Segunda Guerra Mundial, na França, um grupo de judeus americanos conhecidos como Bastardos espalha o terror entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança quando um evento em seu cinema reunirá os líderes do partido.",
                        true
                    ),
                    Film(
                        7,
                        "Django Livre",
                        "Faroente",
                        "2hr 45min",
                        "No sul dos Estados Unidos, o ex-escravo Django faz uma aliança inesperada com o caçador de recompensas Schultz para caçar os criminosos mais procurados do país e resgatar sua esposa de um fazendeiro que força seus escravos a participar de competições mortais.",
                        false
                    ),
                ),
                image = R.drawable.tarantino
            ),
        )
    )

    val listDirector: LiveData<List<Director>>
        get(){
            return _listDirector
        }

    fun getDirector(id: Int): Director{
        _listDirector.value?.forEach{
            if(id == it.id)
                return it
        }
        return Director(
            -1,
            "",
            -1,
            listOf<Film>(),
            -1
        )
    }

    fun getDirectorFilms(id: Int): List<Film>{
        _listDirector.value?.forEach{
            if(id == it.id)
                return it.films
        }
        return listOf<Film>()
    }

    fun getExpecificFilm(idDiretor: Int, idFilm: Int) : Film{
        val listFilms = getDirectorFilms(idDiretor)
        listFilms.forEach{
            if(idFilm == it.id){
                return it
            }
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

    fun refreshFilms(idDir: Int, listFilm: List<Film>){
        val copyDir: MutableList<Director> = _listDirector.value?.toMutableList() ?: return
        copyDir[idDir].films = listFilm
        _listDirector.value = copyDir
    }

}