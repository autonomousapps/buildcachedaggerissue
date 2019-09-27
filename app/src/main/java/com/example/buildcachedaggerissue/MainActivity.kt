package com.example.buildcachedaggerissue

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var magic: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, magic, Toast.LENGTH_LONG).show()
    }
}

@Module
abstract class MainActivityModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesMagic() = "Fourty-two"
    }
}
