package omg.medvedomg.dbofcarowners.mvp.presenter

import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.models.Owner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import omg.medvedomg.dbofcarowners.other.repository.OwnersRepository
import omg.medvedomg.dbofcarowners.other.repository.specification.AllOwnersSpecification
import java.util.*


/**
 * Created by medvedomg on 22.07.17.
 */
class ListOfOwnersPresenter(var listOfOwnersView: ListOfOwnersView,
                            var ownersRepository: OwnersRepository) : Presenter{
//
    fun getListOfOwners() {

        ownersRepository.query(AllOwnersSpecification())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    listOfOwnersView.showOwners(it)
                })

    }

    fun saveOwner(owner: Owner){

        ownersRepository.add(Collections.singletonList(owner))
                    .subscribeOn(Schedulers.io())
                    .map { convert ->  getListOfOwners()}
                    .subscribe()

    }

    fun deleteOwner(owner: Owner) {
        ownersRepository.remove(owner)
                .subscribeOn(Schedulers.io())
                .map { convert ->  getListOfOwners()}
                .subscribe()

    }

    fun updateOwner(owner: Owner) {

        ownersRepository.update(owner)
                .subscribeOn(Schedulers.io())
                .map { convert ->  getListOfOwners()}
                .subscribe()

    }
}