package com.alvayonara.moviecatalogue.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alvayonara.moviecatalogue.R
import com.alvayonara.moviecatalogue.data.source.local.entity.MovieEntity
import com.alvayonara.moviecatalogue.ui.movie.MovieAdapter
import com.alvayonara.moviecatalogue.ui.movie.MovieAdapter.Companion.TYPE_LIST
import com.alvayonara.moviecatalogue.utils.ToolbarConfig
import com.alvayonara.moviecatalogue.utils.gone
import com.alvayonara.moviecatalogue.utils.invisible
import com.alvayonara.moviecatalogue.utils.visible
import com.alvayonara.moviecatalogue.viewmodel.ViewModelFactory
import com.alvayonara.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var query: String
    private var listMovies = ArrayList<MovieEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initToolbar()

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[SearchViewModel::class.java]

        initComponent(viewModel)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setWhiteStatusBarColor(this)
        ToolbarConfig.setLightStatusBar(this)
    }

    private fun initComponent(viewModel: SearchViewModel) {
        searchAdapter = SearchAdapter()

        btn_clear_search.gone()
        btn_clear_search.setOnClickListener {
            edt_search.setText("")
            clearRecyclerView()
            lyt_not_found_search.gone()
            lyt_search.visible()
        }

        edt_search.addTextChangedListener(textWatcher)
        edt_search.setOnEditorActionListener(OnEditorActionListener { _, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || keyEvent.action == KeyEvent.ACTION_DOWN
                || keyEvent.action == KeyEvent.KEYCODE_ENTER){

                clearRecyclerView()
                lyt_not_found_search.gone()

                hideKeyboard()
                searchAction(viewModel)
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun clearRecyclerView(){
        listMovies.clear()
        searchAdapter.setMovies(listMovies)
        searchAdapter.notifyDataSetChanged()
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {
            if (c.toString().trim { it <= ' ' }.isEmpty()) {
                btn_clear_search.gone()
            } else {
                btn_clear_search.visible()
            }
        }

        override fun beforeTextChanged(c: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            if (TextUtils.isEmpty(editable.toString().trim())) {
                clearRecyclerView()
                lyt_not_found_search.gone()
                lyt_search.visible()
            }
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun searchAction(viewModel: SearchViewModel) {
        lyt_search.gone()
        lyt_not_found_search.gone()

        query = edt_search.text.toString().trim()
        if (query != "") {
            viewModel.getMovieSearch("%$query%").observe(this, Observer { movies ->
                when (movies.status) {
                    Status.LOADING -> {
                        progress_bar_movie_search.visible()
                    }
                    Status.SUCCESS -> {
                        progress_bar_movie_search.invisible()

                        if (movies.data!!.isEmpty()) {
                            lyt_not_found_search.visible()
                        } else {
                            lyt_not_found_search.gone()
                            searchAdapter.setMovies(movies.data)
                            searchAdapter.notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        progress_bar_movie_search.invisible()
                        Toast.makeText(this, "Please input keyword", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })

            with(rv_movie_search) {
                layoutManager = LinearLayoutManager(context)
                adapter = searchAdapter
            }
        } else {
            lyt_search.visible()
            Toast.makeText(this, "Please input keyword", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}