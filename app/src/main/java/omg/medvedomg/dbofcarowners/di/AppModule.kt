package omg.medvedomg.labracodetestapp.di

import dagger.Module
import android.app.Application
import android.content.Context
import dagger.Provides
import javax.inject.Singleton



/**
 * Created by medvedomg on 21.07.17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }


}