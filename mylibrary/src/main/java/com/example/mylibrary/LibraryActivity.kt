package com.example.mylibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_library.*
import javax.inject.Inject

class LibraryActivity : AppCompatActivity() {

    @Inject
    lateinit var thing: Thing

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, thing.string(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}

@Module
abstract class LibraryActivityModule {

    @Binds
    abstract fun bindsThing(impl: ThingImpl): Thing
}
