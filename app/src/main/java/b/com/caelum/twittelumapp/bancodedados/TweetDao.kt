package b.com.caelum.twittelumapp.bancodedados

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import b.com.caelum.twittelumapp.modelo.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)

    @Query("select * from Tweet")
    fun listar():List<Tweet>
}