package b.com.caelum.twittelumapp.Repository

import b.com.caelum.twittelumapp.bancodedados.TweetDao
import b.com.caelum.twittelumapp.modelo.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {
    fun lista() = fonteDeDados.listar()
    fun salva(tweet: Tweet)= fonteDeDados.salva(tweet)
    fun deleta(tweet:Tweet)= fonteDeDados.deleta(tweet)
}