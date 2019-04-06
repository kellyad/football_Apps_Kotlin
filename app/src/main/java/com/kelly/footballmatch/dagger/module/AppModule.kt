package com.kelly.footballmatch.dagger.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.kelly.footballmatch.FootBallClub
import com.kelly.footballmatch.dagger.scope.AppScope
import com.kelly.footballmatch.data.network.MyDatabaseOpenHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

//    @Provides
//    @AppScope
//    fun provideScreenRouter(): ScreenRouter = ScreenRouterImpl()

    @Provides
    @AppScope
    fun context(application: FootBallClub): Context = application

    @Provides
    @AppScope
    fun gson() = Gson()

    @Provides
    @AppScope
    fun provideDBHelper(context: Context): MyDatabaseOpenHelper = MyDatabaseOpenHelper(context)

//    @Provides
//    @AppScope
//    fun helper() = Helper()

//    @Provides
//    @AppScope
//    fun provideSchedulerProvider() = AppSchedulerProvider()
}