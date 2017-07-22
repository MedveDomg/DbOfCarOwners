package omg.medvedomg.dbofcarowners.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.mvp.model.models
import omg.medvedomg.dbofcarowners.mvp.presenter.ListOfOwnersPresenter
import omg.medvedomg.dbofcarowners.mvp.presenter.Presenter
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
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
        setContentView(R.layout.activity_main)

        if (presenter != null) {
            Timber.d("presenter != null")
            (presenter as ListOfOwnersPresenter).getListOfOwners()
        }

        rvOwners.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            rvOwners.adapter = adapterOwners
        }

    }


    override fun showOwners(owners: List<models.Owner>) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.)
        return super.onCreateOptionsMenu(menu)
    }
}
