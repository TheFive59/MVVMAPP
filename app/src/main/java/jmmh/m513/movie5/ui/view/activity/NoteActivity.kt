package jmmh.m513.movie5.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import jmmh.m513.movie5.R
import jmmh.m513.movie5.ui.viewmodel.QuoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import javax.inject.Inject
@AndroidEntryPoint
class NoteActivity : AppCompatActivity(){
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        //supportActionBar?.hide()
        quoteViewModel.onCreate()
        //Cuando el live data tiene un cambio se ejecuta lo que este dentro de observe
        quoteViewModel.quoteModel.observe(this, Observer {
                currentQuote->
            tvQuote.text = currentQuote.quote
            tvAuthor.text = currentQuote.author
        })
        quoteViewModel.isLoading.observe(this,Observer{
            //it es quien contiene el live data
            progressBar.isVisible=it
        })
        viewContainer.setOnClickListener{quoteViewModel.randomQuote()}
    }
}