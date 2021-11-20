package com.tomerpacific.laundry

import android.content.Context
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
        val inflater: LayoutInflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val gridItemView = inflater.inflate(R.layout.grid_view_item, null)
        val imageButton: ImageButton = gridItemView.findViewById(R.id.image_button)

        val manager = (gridItemView.context as FragmentActivity).supportFragmentManager
        val laundrySymbol : LaundrySymbol = data[position]
        imageButton.apply {
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

        Utilities.setTooltipForSymbol(imageButton)

        return gridItemView
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

}