package omg.medvedomg.labracodetestapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import omg.medvedomg.dbofcarowners.di.ListOfOwnersActivityModule
import omg.medvedomg.dbofcarowners.ui.ListOfOwnersActivity
import javax.inject.Singleton

/**
 * Created by medvedomg on 21.07.17.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(ListOfOwnersActivityModule::class))
    abstract fun bindListOfOwnersActivity() : ListOfOwnersActivity

}