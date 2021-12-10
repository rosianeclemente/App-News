package com.example.app_news.adapter

import android.util.Log
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
import com.example.app_news.databinding.ItensViewsBinding
import com.example.app_news.model.Article
import kotlinx.android.synthetic.main.itens_views.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItensViewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
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
            ItensViewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        with(holder){
            with(differ.currentList[position]){
                Glide.with(holder.itemView.context).load(urlToImage).into(binding.ivArticleImage)
                binding.tvTitle.text = author ?: source?.name // pode ser substituido por"Autor nao encontrado"
                binding.tvDescription.text = description
                binding.tvSource.text = source?.name ?: author
                binding.tvPublishedAt.text = publishedAt?.getDateTimeFormatted()

                holder.itemView.setOnClickListener { onItemClickListener?.let { click ->
                    click(this)
                }

                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }



   private fun String.getDateTimeFormatted(): String {


        try {

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", getLocale())
            val date = dateFormat.parse(this)
            date?.let {

                return getDateToStringFormatted(date, "dd-MM-yyyy") + " - " + this.substring(11..18)
            }
        } catch (e: ParseException) {
            e.localizedMessage?.let {
                Log.d("TAG", "getDateTimeFormatted: $e")
            }
        }
        return orEmpty()
    }

    fun getDateToStringFormatted(date: Date, dateString: String): String {
        val simpleDateFormat = SimpleDateFormat(dateString, getLocale())
        return simpleDateFormat.format(date)
    }

    fun getLocale(): Locale {
        return Locale("pt", "BR")
    }

}



