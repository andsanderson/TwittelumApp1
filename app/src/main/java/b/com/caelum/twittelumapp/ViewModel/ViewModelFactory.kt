package b.com.caelum.twittelumapp.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import b.com.caelum.twittelumapp.Repository.TweetRepository

object ViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    private fun repository() = TweetRepository(Injetor.tweetDao())
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>):T=TweetViewModel(repository())as T

}