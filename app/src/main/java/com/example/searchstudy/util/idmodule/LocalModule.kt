package com.example.searchstudy.util.idmodule

import android.content.Context
import android.content.SharedPreferences
import com.example.searchstudy.util.Pref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun proviedeEncryptedSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName+ "_preferences", Context.MODE_PRIVATE)
    }
}