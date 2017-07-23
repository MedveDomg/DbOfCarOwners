package omg.medvedomg.dbofcarowners.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.owner_item.view.*
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.other.models.Owner
import omg.medvedomg.labracodetestapp.other.addHas
import omg.medvedomg.labracodetestapp.other.getAllNamesInOneString
import omg.medvedomg.labracodetestapp.other.inflate

/**
 * Created by medvedomg on 22.07.17.
 */
class OwnerInformationViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.owner_item)) {

    fun bind(item: Owner) = with(itemView) {
        tvOwnerName.text = item?.name?.toString()?.addHas()
        tvOwnersCars.text = item?.cars?.getAllNamesInOneString()
    }
}