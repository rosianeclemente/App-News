package com.example.app_news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_news.R
import com.example.app_news.databinding.ActivityArticleBinding
import com.example.app_news.databinding.ActivityMainBinding
import com.example.app_news.databinding.ActivitySearchBinding
import com.example.app_news.model.Article
import kotlinx.android.synthetic.main.itens_views.view.*

class MainAdapter :RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private val diffCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.itens_views, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
       holder.itemView.apply {
           Glide.with(this).load(article.urlToImage).into(ivArticleImage)
           tvTitle.text = article.author ?: article.source?.name // pode ser substituido por"Autor nao encontrado"
           tvDescription.text = article.description
           tvSource.text = article.source?.name  ?: article.author
           tvPublishedAt.text = article.publishedAt

           setOnClickListener{
               onItemClickListener?.let{click ->
                   click(article)
               }
           }

       }

    }
    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }







}