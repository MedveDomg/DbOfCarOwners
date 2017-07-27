package omg.medvedomg.dbofcarowners.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
            layoutManager = linearLayoutManager as RecyclerView.LayoutManager?
            rvOwners.adapter = adapterOwners
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(omg.medvedomg.dbofcarowners.R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        getOwnerEditDialog(null)

        return super.onOptionsItemSelected(item)
    }

    private fun getOwnerEditDialog(owner: Owner?) {

        var edit = false

        if (owner != null) {
            edit = true
        }

        val dialog = MaterialDialog.Builder(this)
                .title(R.string.do_you_want_to_add_an_owner)
                .customView(R.layout.add_owner_dialog, true)
                .positiveText(R.string.save)
                .negativeText(R.string.cancel)
                .show();

        dialog.view.etName.setText(owner?.name ?: "")
        dialog.view.etCar0.setText(owner?.cars?.get(0)?.brand ?: "")
        dialog.view.etCar1.setText(owner?.cars?.get(1)?.brand ?: "")
        dialog.view.etCar2.setText(owner?.cars?.get(2)?.brand ?: "")

        //set clicked on positive button
        dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(View.OnClickListener {

            owner?.name =  dialog.view.etName.text.toString()

            var carList: ArrayList<Car> = ArrayList()

            carList.add(Car(dialog.view.etCar0.text?.toString()))
            carList.add(Car(dialog.view.etCar1.text?.toString()))
            carList.add(Car(dialog.view.etCar2.text?.toString()))

            if (edit) {
                (presenter as ListOfOwnersPresenter).updateOwner(owner,
                        carList)
            } else {
                var owner = Owner(null, dialog.view.etName?.text?.toString(), null)
                (presenter as ListOfOwnersPresenter).saveOwner(owner,
                        carList)
            }


            dialog.dismiss()
        })

        dialog.getActionButton(DialogAction.NEGATIVE).setOnClickListener({ dialog.dismiss() })
    }

    override fun showOwners(owners: List<Owner>) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            // Current Thread is Main Thread.
            Timber.d("UI THREAD")
        } else {
            Timber.d("BACKGROUND THREAD")

        }

        adapterOwners.updateOwners(owners)
    }

    override fun showQuestionActionDialog(owner: Owner) {
        MaterialDialog.Builder(this)
                .title(R.string.what_do_you_want_to_do_with_owner)
                .items(R.array.actions_with_owner)
                .itemsCallbackSingleChoice(-1, MaterialDialog.ListCallbackSingleChoice { dialog, view, which, text ->
                    when (which) {
                        0 -> {
                            getOwnerEditDialog(owner)
                        }
                        1 -> {
                            //delete row
                            (presenter as ListOfOwnersPresenter).deleteOwner(owner)

                        }
                    }
                    true
                })
                .show()
    }

    override fun showEditDialog(owner: Owner) {
        getOwnerEditDialog(owner)
    }
}
