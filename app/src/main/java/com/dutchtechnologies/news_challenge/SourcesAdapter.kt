package com.dutchtechnologies.news_challenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import host
import kotlinx.android.synthetic.main.view_holder_source.view.*
import load
import kotlin.properties.Delegates

class SourcesAdapter : RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    var items: List<Source> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    lateinit var click: View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.view_holder_source,
            parent,
            false
        ), click
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.containerView.setOnClickListener(click)
    }


    class ViewHolder(var containerView: View, var onClick: View.OnClickListener? = null) :
        RecyclerView.ViewHolder(containerView) {


        fun bind(source: Source) {
            containerView.tag = this
            containerView.setOnClickListener(onClick)
            with(source) {
                val thumb = source.url.host()
                containerView.view_holder_sources_thumbnail.load("https://icon-locator.herokuapp.com/icon?url=$thumb&size=70..120..200")
                containerView.view_holder_sources_title.text = title
                containerView.view_holder_sources_description.text = description
                containerView.view_holder_sources_url.text = url
            }
        }
    }
}