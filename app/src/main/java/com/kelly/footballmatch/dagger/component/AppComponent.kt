package com.kelly.footballmatch.dagger.component

import com.kelly.footballmatch.FootBallClub
import com.kelly.footballmatch.dagger.module.AppModule
import com.kelly.footballmatch.dagger.module.builder.ActivityBuilder
import com.kelly.footballmatch.dagger.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@AppScope
@Component(
        modules = [
            (AndroidInjectionModule::class),
            (AppModule::class),
            (ActivityBuilder::class)
        ]
)

interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : FootBallClub ): Builder
        fun build(): AppComponent
    }
    fun inject(app: FootBallClub)
}