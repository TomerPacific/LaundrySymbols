package com.tomerpacific.laundry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.tomerpacific.laundry.fragments.LaundrySymbolFragment

class GridViewAdapter(private var data: List<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val gridItemView = inflater.inflate(R.layout.grid_view_item, null)
        val imageButton: ImageButton = gridItemView.findViewById(R.id.image_button)
        val identifier: Int? = parent.context?.resources?.getIdentifier(
            data[position],
            "drawable",
            parent.context?.packageName)

        val manager = (gridItemView.context as FragmentActivity).supportFragmentManager

        imageButton.apply {
            scaleType = ImageView.ScaleType.CENTER_INSIDE
            setImageResource(identifier!!)
            tag = data[position]
            contentDescription = Utilities.getSymbolDescription(data[position])
            setOnClickListener {
                val fragment : LaundrySymbolFragment = LaundrySymbolFragment.newInstance(data[position], identifier)
                manager.beginTransaction()?.
                replace(R.id.fragment_container_view, fragment)?.
                addToBackStack(null)?.commit()
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