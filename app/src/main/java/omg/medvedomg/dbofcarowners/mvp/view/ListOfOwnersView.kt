package omg.medvedomg.dbofcarowners.mvp.view

import omg.medvedomg.dbofcarowners.other.models.Owner

/**
 * Created by medvedomg on 22.07.17.
 */
interface ListOfOwnersView {
    fun showOwners(owners: List<Owner>)
    fun showQuestionActionDialog(owner: Owner)
    fun showEditDialog(owner: Owner)
}