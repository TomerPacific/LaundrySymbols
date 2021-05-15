package com.tomerpacific.laundry

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class GridViewAdapter(var data: List<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflator: LayoutInflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val gridItemView = inflator.inflate(R.layout.grid_view_item, null)
        val imageView: ImageView = gridItemView.findViewById(R.id.image_button)
        val identifier: Int? = parent.context?.resources?.getIdentifier(
            data[position],
            "drawable",
            parent.context?.packageName)

        imageView.apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            setImageResource(identifier!!)
            tag = data[position]
        }

        return gridItemView
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return data[p0].toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

}