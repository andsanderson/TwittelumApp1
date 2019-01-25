package b.com.caelum.twittelumapp.ViewModel

import android.arch.lifecycle.ViewModel
import b.com.caelum.twittelumapp.Repository.TweetRepository
import b.com.caelum.twittelumapp.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository): ViewModel() {
    fun lista() = repository.lista()
    fun salva(tweet: Tweet)= repository.salva(tweet)
    fun deleta(tweet: Tweet)= repository.deleta(tweet)
}