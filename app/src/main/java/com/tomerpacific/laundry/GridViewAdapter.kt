package com.tomerpacific.laundry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.tomerpacific.laundry.fragment.LaundrySymbolFragment
import com.tomerpacific.laundry.model.LaundrySymbol

class GridViewAdapter(private var data: List<LaundrySymbol>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val laundrySymbol : LaundrySymbol = data[position]
        var convertViewMutable = convertView
        if (convertViewMutable == null) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            convertViewMutable = layoutInflater.inflate(R.layout.grid_view_item, parent, false)
            val holder = LaundrySymbolHolder(convertViewMutable.findViewById(R.id.image_button))
            convertViewMutable.tag = holder
        }

        val holder = convertViewMutable?.tag as LaundrySymbolHolder

        holder.imageButton.apply {
            val manager = (convertViewMutable.context as FragmentActivity).supportFragmentManager
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(laundrySymbol.drawableId)
            tag = data[position]
            contentDescription = laundrySymbol.description
            setOnClickListener {
                val fragment : LaundrySymbolFragment = LaundrySymbolFragment.newInstance(laundrySymbol)
                manager.beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        Utilities.setTooltipForSymbol(holder.imageButton)

       return convertViewMutable
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    private class LaundrySymbolHolder(laundryImageButton: ImageButton) {
        var imageButton: ImageButton = laundryImageButton
    }

}