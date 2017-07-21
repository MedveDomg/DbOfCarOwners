package omg.medvedomg.dbofcarowners.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import omg.medvedomg.dbofcarowners.R
import omg.medvedomg.dbofcarowners.mvp.presenter.Presenter
import omg.medvedomg.dbofcarowners.ui.adapter.ListOfOwnersAdapter
import javax.inject.Inject

class ListOfOwnersActivity : AppCompatActivity() {

//    @Inject lateinit var linearLayoutManager: LinearLayoutManager
//    @Inject lateinit var presenter: Presenter
//    @Inject lateinit var adapterOwners: ListOfOwnersAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
