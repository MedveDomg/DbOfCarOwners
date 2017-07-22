package omg.medvedomg.dbofcarowners.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import omg.medvedomg.dbofcarowners.mvp.view.ListOfOwnersView
import omg.medvedomg.dbofcarowners.ui.ListOfOwnersActivity
import omg.medvedomg.labracodetestapp.di.PerActivity

/**
 * Created by medvedomg on 22.07.17.
 */
@Module
class ListOfOwnersActivityModule {

    //TODO add presenter, adapter

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
    fun provideAdapter
}