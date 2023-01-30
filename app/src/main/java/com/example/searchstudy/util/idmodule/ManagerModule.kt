package com.example.searchstudy.util.idmodule

import com.example.searchstudy.network.managers.SearchManager
import com.example.searchstudy.network.managers.SearchManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindSearchManager(searchManagerImpl: SearchManagerImpl): SearchManager
}