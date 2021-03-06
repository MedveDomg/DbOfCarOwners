package omg.medvedomg.dbofcarowners.mvp.presenter

import android.content.Context
import io.reactivex.Observable
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.models.Owner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.other.repository.OwnersRepository
import omg.medvedomg.dbofcarowners.other.repository.specification.AllOwnersSpecification
import java.util.*


/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersPresenter(var listOfOwnersView: ListOfOwnersView,
//                            var ownersSqlRepository: OwnersRepository) : Presenter{
                            var dbHelper: DbHelper) : Presenter{
//
    fun getListOfOwners() {

        Observable.fromCallable({dbHelper.getAllOwners()})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map ({ list ->
                    listOfOwnersView.showOwners(list)
                })
                .subscribe()
    }

    fun saveOwner(owner: Owner, cars: List<Car>){

                Observable.fromCallable({
                            dbHelper.createOwner(owner,cars)
                        })
                .subscribeOn(Schedulers.io())
                .map { convert ->  dbHelper.getAllOwners()}
                .observeOn(AndroidSchedulers.mainThread())
                .map { list ->   listOfOwnersView.showOwners(list)}
                .subscribe()
    }

    fun deleteOwner(owner: Owner) {
                Observable.fromCallable({
                    dbHelper.deleteOwner(owner)
                })
                .subscribeOn(Schedulers.io())
                .map { convert ->  dbHelper.getAllOwners()}
                .observeOn(AndroidSchedulers.mainThread())
                .map { list ->   listOfOwnersView.showOwners(list)}
                .subscribe()
    }

    fun updateOwner(owner: Owner?, cars: List<Car>) {
                Observable.fromCallable({
                    dbHelper.updateOwner(owner,cars)
                })
                .subscribeOn(Schedulers.io())
                .map { convert ->  dbHelper.getAllOwners()}
                .observeOn(AndroidSchedulers.mainThread())
                .map { list ->   listOfOwnersView.showOwners(list)}
                .subscribe()
    }
}