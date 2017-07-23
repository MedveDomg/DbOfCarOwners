package omg.medvedomg.dbofcarowners.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.models.Owner
import omg.medvedomg.dbofcarowners.ui.adapter.viewholder.OwnerInformationViewHolder
import timber.log.Timber

/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersAdapter(val context: Context, val listOfOwnersView: ListOfOwnersView) : RecyclerView.Adapter<OwnerInformationViewHolder>() {

    private var owners: ArrayList<Owner>

    init {
        owners = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerInformationViewHolder{
        return OwnerInformationViewHolder(parent)
    }

    override fun onBindViewHolder(holder: OwnerInformationViewHolder, position: Int) {
        holder.bind(owners.get(position))

        holder.itemView.setOnClickListener({ Timber.d("click position: $position")})
        holder.itemView.setOnLongClickListener({
            listOfOwnersView.showDeleteOrEditDialog(owners.get(position))
            true
        })
    }

    override fun getItemCount(): Int {
        return owners.size
    }

    fun  updateOwners(owners: List<Owner>) {
        this.owners.clear()
        this.owners.addAll(owners)
    }
}