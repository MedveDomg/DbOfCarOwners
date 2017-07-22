package omg.medvedomg.dbofcarowners.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import omg.medvedomg.dbofcarowners.mvp.model.models
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.ui.adapter.viewholder.OwnerInformationViewHolder
import timber.log.Timber

/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersAdapter(val context: Context) : RecyclerView.Adapter<OwnerInformationViewHolder>() {

    private var owners: List<models.Owner>

    init {
        owners = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerInformationViewHolder{
        return OwnerInformationViewHolder(parent)
    }

    override fun onBindViewHolder(holder: OwnerInformationViewHolder, position: Int) {
        //TODO uncomment when make data available
//        holder.bind(owners.get(position))

        holder.itemView.setOnClickListener({ Timber.d("click position: $position")})
    }

    override fun getItemCount(): Int {
        return 10
    }
}