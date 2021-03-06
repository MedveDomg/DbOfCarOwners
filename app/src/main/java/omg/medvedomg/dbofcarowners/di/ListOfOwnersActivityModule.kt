package omg.medvedomg.dbofcarowners.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import omg.medvedomg.dbofcarowners.mvp.presenter.ListOfOwnersPresenter
import omg.medvedomg.dbofcarowners.mvp.presenter.Presenter
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.other.repository.OwnersRepository
import omg.medvedomg.dbofcarowners.ui.ListOfOwnersActivity
import omg.medvedomg.dbofcarowners.ui.adapter.ListOfOwnersAdapter

/**
 * Created by medvedomg on 22.07.17.
 */
@Module
class ListOfOwnersActivityModule {

//    @PerActivity
    @Provides
    fun provideListOfOwnersActivity(view: ListOfOwnersActivity) : ListOfOwnersView{
        return view
    }

    @Provides
    fun provideLinearLayoutManager(application : Context) : LinearLayoutManager {
        return LinearLayoutManager(application)
    }

    @Provides
    fun provideListOfOwnersAdapter(context: Context, listOfOwnersView: ListOfOwnersView):ListOfOwnersAdapter{
        return ListOfOwnersAdapter(context,listOfOwnersView)
    }

    @Provides
    fun provideListOfOwnersPresenter(listOfOwnersView: ListOfOwnersView,
                                     dbHelper: DbHelper) : Presenter{
        return ListOfOwnersPresenter(listOfOwnersView, dbHelper)
    }
}