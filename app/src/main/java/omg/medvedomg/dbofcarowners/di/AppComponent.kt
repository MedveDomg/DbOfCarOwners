package omg.medvedomg.labracodetestapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import omg.medvedomg.dbofcarowners.DbOfCarOwnersApp
import omg.medvedomg.dbofcarowners.di.DbModule
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by medvedomg on 21.07.17.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,ActivityBuilder::class,AppModule::class,DbModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application) : Builder
        fun build() : AppComponent
    }


    fun inject(app: DbOfCarOwnersApp)
}