package com.example.practice.presentation.home.di

import com.example.practice.data.persistence.IDataManager
import com.example.practice.data.persistence.firestore.FirestoreManager
import com.example.practice.domain.repository.home.HomeRepository
import com.example.practice.domain.repository.home.IHomeRepository
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun providesRepository(dataManager: FirestoreManager): IHomeRepository = HomeRepository(dataManager)
    @Provides
    fun providesHomeVMFactory(repo: IHomeRepository): HomeVMFactory = HomeVMFactory(repo)
}