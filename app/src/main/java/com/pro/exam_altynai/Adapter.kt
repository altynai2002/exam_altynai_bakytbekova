package com.pro.exam_altynai

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pro.exam_altynai.database.CharActer


class ItemAdapter(private val click: (id: Long) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var list = listOf<CharActer>()

    fun setData(list: List<CharActer>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycle, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View, private val click: (id: Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CharActer) {
            val image = itemView.findViewById<AppCompatImageView>(R.id.image)
            val txt = itemView.findViewById<AppCompatTextView>(R.id.itemTxt)
            val status = itemView.findViewById<AppCompatTextView>(R.id.status)
            val species = itemView.findViewById<AppCompatTextView>(R.id.species)
            val location = itemView.findViewById<AppCompatTextView>(R.id.location)
            txt.text = item.name
            status.text = item.status
            if(item.status == "Dead") {
                status.setTextColor(Color.RED)
            }
            else if (item.status == "Alive") {
                status.setTextColor(Color.GREEN)
            }
            else {
                status.setTextColor(Color.WHITE)
            }
            species.text = item.species
            location.text = item.location
            Glide.with(itemView.context).load(item.image).into(image)

            itemView.setOnClickListener {
                Log.e("TAG", "doOnSuccess $item")
                click.invoke(item.id!!)
            }
        }
    }
}