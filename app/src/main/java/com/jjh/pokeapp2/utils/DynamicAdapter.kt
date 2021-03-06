package com.jjh.pokeapp2.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DynamicAdapter<T>(
        val layout: Int,
        val entries: List<T>,
        val action: (vh: DynamicAdapter.ViewHolder, view: View, entry: T, position: Int) -> Unit
): RecyclerView.Adapter<DynamicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynamicAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        action.invoke(holder, holder.view, entries.get(position), position)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    class ViewHolder (v: View) : RecyclerView.ViewHolder(v) {
        val view = v
    }

}