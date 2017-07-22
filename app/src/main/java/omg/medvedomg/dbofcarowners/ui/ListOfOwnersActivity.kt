package omg.medvedomg.dbofcarowners.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.mvp.presenter.Presenter
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.ui.adapter.ListOfOwnersAdapter
import timber.log.Timber
import javax.inject.Inject

class ListOfOwnersActivity : AppCompatActivity(),ListOfOwnersView {


    @Inject lateinit var linearLayoutManager: LinearLayoutManager
    @Inject lateinit var presenter: Presenter
    @Inject lateinit var adapterOwners: ListOfOwnersAdapter
    @Inject lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (dbHelper != null) {
            Timber.d("db helper != null")
        }
    }

    override fun showOwners() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
