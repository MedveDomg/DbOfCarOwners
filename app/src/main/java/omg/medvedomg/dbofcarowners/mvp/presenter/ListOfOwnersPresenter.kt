package omg.medvedomg.dbofcarowners.mvp.presenter

import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.mvp.model.models
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import timber.log.Timber

/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersPresenter(var listOfOwnersView: ListOfOwnersView,
                            var dbHelper: DbHelper) : Presenter{

    fun getListOfOwners() {
        if (dbHelper != null) {
            Timber.d("dbHelper != null")
        }
    }

    fun setListOfOwners(owners: List<models.Owner>) {
        listOfOwnersView.showOwners(owners)
    }
}