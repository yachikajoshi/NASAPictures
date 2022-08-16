package com.aks.nasapictures.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aks.nasapictures.databinding.PictureItemBinding
import com.aks.nasapictures.ui.datasource.model.PictureData
import com.aks.nasapictures.ui.utils.bindImageUrl

class PictureAdapter(private var itemClickListener: (position: Int) -> Unit) :
    ListAdapter<PictureData, PictureAdapter.PictureViewHolder>(PicturesDiffUtil) {

    inner class PictureViewHolder(var pictureItemBinding: PictureItemBinding) :
        RecyclerView.ViewHolder(pictureItemBinding.root) {
        init {
            pictureItemBinding.image.setOnClickListener { itemClickListener(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            PictureItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.pictureItemBinding.image.bindImageUrl(getItem(position).url)
    }

    private object PicturesDiffUtil : DiffUtil.ItemCallback<PictureData>() {
        override fun areItemsTheSame(
            oldItem: PictureData,
            newItem: PictureData
        ) = newItem.url == oldItem.url

        override fun areContentsTheSame(
            oldItem: PictureData,
            newItem: PictureData
        ) = newItem == oldItem

    }
}