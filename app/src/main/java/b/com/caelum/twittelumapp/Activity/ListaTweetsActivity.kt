package b.com.caelum.twittelumapp.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import b.com.caelum.twittelumapp.R
import b.com.caelum.twittelumapp.ViewModel.TweetViewModel
import b.com.caelum.twittelumapp.ViewModel.ViewModelFactory
import b.com.caelum.twittelumapp.bancodedados.TwittelumDatabase
import b.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_lista_tweets.*


class ListaTweetsActivity: AppCompatActivity() {

    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_tweets)

        viewModel = ViewModelProviders.of(this,ViewModelFactory).get(TweetViewModel::class.java)
        viewModel.lista().observe(this,observer())

        ftbAddTweet.setOnClickListener {
            /* Snackbar.make(it,"Botão clicado",Snackbar.LENGTH_LONG).show()*/
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }
        val listener = AdapterView.OnItemClickListener { adapter, item, posicao, id ->
            val tweet = lstTweets.getItemAtPosition(posicao) as Tweet
            perguntaSePrecisaDeletarEsse(tweet)

        }
        lstTweets.onItemClickListener = listener

    }

    private fun observer(): Observer<List<Tweet>> {
        return Observer {
            lstTweets.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,it)
        }
    }

    private fun perguntaSePrecisaDeletarEsse( tweet:Tweet){
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Deseja apagar o tweet?")
        dialog.setTitle("Alerta")
        dialog.setPositiveButton("SIM"){_,_->viewModel.deleta(tweet)}
        dialog.setNegativeButton("NÂO",null)
        dialog.show()
    }

    /*override fun onResume() {
        super.onResume()

        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        val tweets:List<Tweet> = tweetDao.listar()
        val adapters = ArrayAdapter<Tweet>(this,android.R.layout.simple_list_item_1,tweets)
        lstTweets.adapter = adapters

    }*/
}