package omg.medvedomg.dbofcarowners

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import omg.medvedomg.labracodetestapp.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by medvedomg on 22.07.17.
 */
class DbOfCarOwnersApp : Application(),HasActivityInjector{

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}