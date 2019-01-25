package b.com.caelum.twittelumapp.ViewModel

import b.com.caelum.twittelumapp.application.TwittelumApplication
import b.com.caelum.twittelumapp.bancodedados.TwittelumDatabase

object Injetor {
    private val contexto = TwittelumApplication.getInstance()
    private val database = TwittelumDatabase.getInstance(contexto)
    fun tweetDao() = database.tweetDao()
}