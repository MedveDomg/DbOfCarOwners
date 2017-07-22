package omg.medvedomg.dbofcarowners.mvp.view

import omg.medvedomg.dbofcarowners.mvp.model.models

/**
 * Created by medvedomg on 22.07.17.
 */
interface ListOfOwnersView {
    fun showOwners(owners: List<models.Owner>)
}