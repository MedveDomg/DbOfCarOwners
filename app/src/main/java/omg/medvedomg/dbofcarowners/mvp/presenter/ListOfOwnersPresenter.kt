package omg.medvedomg.dbofcarowners.mvp.presenter

import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.models.Owner
import timber.log.Timber

/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersPresenter(var listOfOwnersView: ListOfOwnersView,
                            var dbHelper: DbHelper) : Presenter{

    fun getListOfOwners() {
        listOfOwnersView.showOwners(dbHelper.getAllOwners())
    }

    fun setListOfOwners(owners: List<Owner>) {
        listOfOwnersView.showOwners(owners)
    }

    fun saveOwner(owner: Owner?, cars: List<Car>){

        for (item in cars) {
            println(item.brand.toString())
        }

        println(owner?.name)

        dbHelper.createOwner(owner,cars)
    }

    fun deleteOwner(owner: Owner) {
        dbHelper.deleteOwner(owner)
    }

    fun updateOwner(owner: Owner?, cars: List<Car>) {
        dbHelper.updateOwner(owner,cars)
    }
}