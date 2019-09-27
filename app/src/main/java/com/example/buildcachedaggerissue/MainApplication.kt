package com.example.buildcachedaggerissue

import android.app.Application
import android.content.Context
import com.example.mylibrary.LibraryActivity
import com.example.mylibrary.LibraryActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import javax.inject.Singleton

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var _androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = _androidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerMainApplicationComponent.builder()
            .app(this)
            .build()
            .inject(this)
    }
}

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class
    ]
)
interface MainApplicationComponent {

    fun inject(app: MainApplication)

    @Component.Builder
    interface Builder {
        fun build(): MainApplicationComponent

        @BindsInstance
        fun app(app: Context): Builder
    }
}

@Module
interface ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    fun mainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [
            LibraryActivityModule::class
        ]
    )
    fun libraryActivity(): LibraryActivity
}
