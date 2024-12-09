package com.idcamp.mysubmissionapps

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idcamp.mysubmissionapps.databinding.ItemCardBinding

class ListGameAdapter(private val listHero: ArrayList<Game>) : RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, releaseDate, developer) = listHero[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_game", listHero[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}
