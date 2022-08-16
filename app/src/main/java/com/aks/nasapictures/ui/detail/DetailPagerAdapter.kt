
package com.aks.nasapictures.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aks.nasapictures.databinding.DetailItemBinding
import com.aks.nasapictures.ui.datasource.model.PictureData
import com.aks.nasapictures.ui.utils.bindImageUrl

class DetailPagerAdapter :
    ListAdapter<PictureData, DetailPagerAdapter.DetailPagerViewHolder>(PicturesDiffUtil) {

    inner class DetailPagerViewHolder(var binding: DetailItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPagerViewHolder {
        return DetailPagerViewHolder(
            DetailItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailPagerViewHolder, position: Int) {
        holder.binding.image.bindImageUrl(getItem(position).url)
        holder.binding.copyrightName.text=getItem(position).copyright
        holder.binding.date.text="(${getItem(position).date})"
        holder.binding.explanation.text=getItem(position).explanation
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