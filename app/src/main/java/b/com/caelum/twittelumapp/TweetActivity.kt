package b.com.caelum.twittelumapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TweetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        val btnPublicar = findViewById<Button>(R.id.btnPublicar)

        /*btnPublicar.setOnClickListener {
            val editTweet = findViewById<EditText>(R.id.edtTweet)

            val texto = editTweet.text

            Toast.makeText(this,texto,Toast.LENGTH_LONG).show()
        }*/

        btnPublicar.setOnClickListener {
            MostrarToast()
        }
    }


    private fun MostrarToast ()
    {
        val editTweet = findViewById<EditText>(R.id.edtTweet)
        val texto = editTweet.text

        Toast.makeText(this,texto,Toast.LENGTH_LONG).show()
    }
}
