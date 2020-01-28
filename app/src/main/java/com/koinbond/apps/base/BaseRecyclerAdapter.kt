package com.koinbond.apps.base

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koinbond.apps.utils.RecyclerInterface

abstract class BaseRecyclerAdapter<Model, VH : BaseViewHolder<Model>> : RecyclerView.Adapter<VH> {

    protected var context: Context
    protected var modelList: MutableList<Model>
    protected var inflater: LayoutInflater

    protected val activity: Activity
        get() = context as Activity

    constructor(context: Context, modelList: MutableList<Model>) {
        this.context = context
        this.modelList = modelList
        inflater = LayoutInflater.from(context)
    }

    constructor(context: Context, modelList: MutableList<Model>, recyclerCallback: RecyclerInterface) {
        this.context = context
        this.modelList = modelList
        inflater = LayoutInflater.from(context)
        this.recyclerCallback = recyclerCallback
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(modelList[position])
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    protected fun initView(viewType: Int, parent: ViewGroup): View {
        return inflater.inflate(getResLayout(viewType), parent, false)
    }

    protected abstract fun getResLayout(type: Int): Int

    private lateinit var recyclerCallback: RecyclerInterface

    fun getRecyclerCallback(): RecyclerInterface {
        return recyclerCallback
    }

    fun insertAndNotify(models: List<Model>?) {
        if (models != null && models.size > 0) {

            activity.runOnUiThread {
                modelList.addAll(models)
                notifyItemRangeInserted(modelList.size - models.size, models.size)
            }
        }
    }

    fun insertsAndNotify(models: MutableList<Model>?){
        if (models != null && models.size > 0) {
            modelList.addAll(models)
            notifyItemRangeInserted(modelList.size - models.size, models.size)
        }
    }

    fun insertAndNotify(model: Model) {

        activity.runOnUiThread {
            modelList.add(model)
            notifyItemRangeInserted(modelList.size - 1, 1)
        }
    }


}