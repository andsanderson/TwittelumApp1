package b.com.caelum.twittelumapp.Activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import b.com.caelum.twittelumapp.R
import b.com.caelum.twittelumapp.ViewModel.Injetor
import b.com.caelum.twittelumapp.ViewModel.TweetViewModel
import b.com.caelum.twittelumapp.ViewModel.ViewModelFactory
import b.com.caelum.twittelumapp.bancodedados.TwittelumDatabase
import b.com.caelum.twittelumapp.extension.decodificaParaBase64
import b.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_tweet.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto:String?=null

    private val viewModel:TweetViewModel by lazy {
        ViewModelProviders.of(this,ViewModelFactory).get(TweetViewModel::class.java)
    }
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


/*    override fun onResume() {
        super.onResume()
        localFoto?.let { CarregaFoto() }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==123)
            if(resultCode==Activity.RESULT_OK){
                CarregaFoto()
            }
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
            R.id.tweet_menu_camera->{
                tiraFoto()
            }

        }
        return true

    }

    private fun CarregaFoto(){
        val bitmap = BitmapFactory.decodeFile(localFoto)
//        val bm = Bitmap.createScaledBitmap(bitmap,bitmap.width,bitmap.height,true)

        val bm = Bitmap.createScaledBitmap(bitmap,300,300,true)
        tweet_foto.setImageBitmap(bm)
        val fotoNaBase64 = bm.decodificaParaBase64()
        tweet_foto.tag = fotoNaBase64
        tweet_foto.scaleType=ImageView.ScaleType.FIT_XY

    }

    private  fun tiraFoto()
    {
        val vaiParaCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = definirLocalDaFoto()
        vaiParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,caminhoFoto)
        startActivityForResult(vaiParaCamera,123)

    }

    fun definirLocalDaFoto(): Uri?{
        localFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this,"MeuProvider",arquivo)
    }

    private  fun publicarTweet()
    {
        val tweet = criarTweet()

        viewModel.salva(tweet)
        //val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        //tweetDao.salva(tweet)
        Toast.makeText(this,"$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()
    }

    private fun criarTweet(): Tweet {
        val mensagemDoTweet: String = edtTweet.text.toString()
        val foto: String?=tweet_foto.tag as String?
        val tweet = Tweet(mensagemDoTweet,foto)
        return tweet
    }


    private fun MostrarToast ()
    {
        val editTweet = findViewById<EditText>(R.id.edtTweet)
        val texto = editTweet.text.toString()

        val tweet = Tweet(texto,null)


        Toast.makeText(this,"$tweet",Toast.LENGTH_LONG).show()
    }
}
