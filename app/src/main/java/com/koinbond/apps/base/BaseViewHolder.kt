package com.koinbond.apps.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.koinbond.apps.utils.RecyclerInterface

abstract class BaseViewHolder<Model> : RecyclerView.ViewHolder {
    constructor(itemView: View) : super(itemView)
    constructor(itemView: View, recyclerInterface: RecyclerInterface) : super(itemView) {
        itemView.setOnClickListener { recyclerInterface.onRecyclerItemClicked(adapterPosition) }
    }

    abstract fun onBind(data: Model)
}