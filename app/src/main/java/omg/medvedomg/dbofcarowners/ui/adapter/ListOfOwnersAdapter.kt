package omg.medvedomg.dbofcarowners.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import omg.medvedomg.dbofcarowners.mvp.model.models
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.ui.adapter.viewholder.OwnerInformationViewHolder

/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersAdapter(val activity: ListOfOwnersView, val context: Context) : RecyclerView.Adapter<OwnerInformationViewHolder>() {

    private var owners: List<models.Owner>

    init {
        owners = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OwnerInformationViewHolder{
        return OwnerInformationViewHolder(parent)
    }

    override fun onBindViewHolder(holder: OwnerInformationViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}