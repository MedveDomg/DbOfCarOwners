package omg.medvedomg.dbofcarowners.di

import android.content.Context
import dagger.Module
import dagger.Provides
import omg.medvedomg.dbofcarowners.mvp.model.DbHelper
import javax.inject.Singleton

/**
 * Created by medvedomg on 22.07.17.
 */
@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(context: Context) : DbHelper{
        return DbHelper(context,"Owners",1)
    }

}