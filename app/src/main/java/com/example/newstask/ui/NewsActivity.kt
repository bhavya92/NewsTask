package com.example.newstask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newstask.R
import com.example.newstask.databinding.ActivityNewsBinding
import com.google.android.material.snackbar.Snackbar


class NewsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsListViewModel
    private var errorSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(NewsListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {

        errorSnackBar = Snackbar.make(binding.root, errorMessage, 2000)
        errorSnackBar?.show()

    }

    private fun hideError() {
        errorSnackBar?.dismiss()
    }
}