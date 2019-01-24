package b.com.caelum.twittelumapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import b.com.caelum.twittelumapp.bancodedados.TwittelumDatabase
import b.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_tweet.*

class TweetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        /*
        val btnPublicar = findViewById<Button>(R.id.btnPublicar)
        btnPublicar.setOnClickListener {
            val editTweet = findViewById<EditText>(R.id.edtTweet)

            val texto = editTweet.text

            Toast.makeText(this,texto,Toast.LENGTH_LONG).show()
        }*/

        /*btnPublicar.setOnClickListener {
            MostrarToast()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.tweet_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId)
        {
            R.id.tweet_menu_cadastrar -> {
                publicarTweet()
                finish()
            }
            android.R.id.home -> finish()

        }
        return true

    }

    private  fun publicarTweet()
    {
        val mensagemDoTweet :String = edtTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        tweetDao.salva(tweet)
        Toast.makeText(this,"$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
    }


    private fun MostrarToast ()
    {
        val editTweet = findViewById<EditText>(R.id.edtTweet)
        val texto = editTweet.text.toString()

        val tweet = Tweet(texto)


        Toast.makeText(this,"$tweet",Toast.LENGTH_LONG).show()
    }
}
