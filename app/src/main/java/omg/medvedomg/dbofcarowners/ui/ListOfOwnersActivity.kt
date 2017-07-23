package omg.medvedomg.dbofcarowners.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_owner_dialog.view.*
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.mvp.presenter.ListOfOwnersPresenter
import omg.medvedomg.dbofcarowners.mvp.presenter.Presenter
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.models.Car
import omg.medvedomg.dbofcarowners.other.models.Owner
import omg.medvedomg.dbofcarowners.ui.adapter.ListOfOwnersAdapter
import timber.log.Timber
import javax.inject.Inject



class ListOfOwnersActivity : AppCompatActivity(),ListOfOwnersView {



    @Inject lateinit var linearLayoutManager: LinearLayoutManager
    @Inject lateinit var presenter: Presenter
    @Inject lateinit var adapterOwners: ListOfOwnersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(omg.medvedomg.dbofcarowners.R.layout.activity_main)

        (presenter as ListOfOwnersPresenter).getListOfOwners()

        rvOwners.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            rvOwners.adapter = adapterOwners
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(omg.medvedomg.dbofcarowners.R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val dialog = MaterialDialog.Builder(this)
                .title(R.string.do_you_want_to_add_an_owner)
                .customView(R.layout.add_owner_dialog, true)
                .positiveText(R.string.save)
                .negativeText(R.string.cancel)
                .show();

        //set clicked on positive button
        dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(View.OnClickListener {

            var owner = Owner(dialog.view.etName?.text?.toString(),null)

            var carList: ArrayList<Car> = ArrayList()

            carList.add(Car(dialog.view.etCar0.text?.toString()))
            carList.add(Car(dialog.view.etCar1.text?.toString()))
            carList.add(Car(dialog.view.etCar2.text?.toString()))

            (presenter as ListOfOwnersPresenter).saveOwner(owner,
                    carList)

            dialog.dismiss()
        })

        dialog.getActionButton(DialogAction.NEGATIVE).setOnClickListener({ dialog.dismiss() })


        return super.onOptionsItemSelected(item)
    }

    override fun showOwners(owners: List<Owner>) {
        for (item in owners) {
            Timber.d(item.name + " cars:")
            for (item2 in (item.cars).orEmpty()) {
                Timber.d(item2.brand)
            }
        }

        adapterOwners.updateOwners(owners)
    }
}
