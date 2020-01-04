package com.example.newstask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newstask.R
import com.example.newstask.databinding.IndexItemBinding

class NewsAdapter(titleList : ArrayList<String>): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private var newsTitleList: ArrayList<String>
    init {
        this.newsTitleList = titleList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: IndexItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.index_item, parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsTitleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsTitleList[position])
    }


    fun updateNewsList(newsList:ArrayList<String>) {
        this.newsTitleList = newsList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: IndexItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NewsViewModel()

        fun bind(news: String) {
            viewModel.bind(news)
            binding.viewModel = viewModel
        }
    }
}