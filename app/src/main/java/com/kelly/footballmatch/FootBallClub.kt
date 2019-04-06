package com.kelly.footballmatch

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.v4.app.Fragment
import com.kelly.footballmatch.dagger.component.AppComponent
import com.kelly.footballmatch.dagger.component.DaggerAppComponent
import com.kelly.footballmatch.koin.modules.Appmodule
import com.kelly.footballmatch.presentation.eventpage.detailpage.module.detailpageModule
import com.kelly.footballmatch.presentation.eventpage.homepage.module.homePageModule
import com.kelly.footballmatch.presentation.eventpage.searchpage.module.searchmatchModule
//import com.kelly.footballmatch.presentation.playerpage.homepage.module.playerModule
//import com.kelly.footballmatch.presentation.teampage.homepage.module.hometeamModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import org.koin.android.ext.android.startKoin
import javax.inject.Inject

class FootBallClub: Application(), HasActivityInjector, HasSupportFragmentInjector {
    companion object {
        @JvmStatic
        lateinit var instance: FootBallClub
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createComponent()
        appComponent.inject(this)
//        startKoin(this, listOf(homePageModule, detailpageModule, searchmatchModule, hometeamModule, playerModule
//        ))
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector

    }
    private fun createComponent(): AppComponent {

        return DaggerAppComponent.builder()
                .application(this)
               // .networkModule(NetworkModule(this))
                .build()
    }
}