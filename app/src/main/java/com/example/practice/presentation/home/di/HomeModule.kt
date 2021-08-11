package com.example.practice.presentation.home.di

import com.example.practice.data.firebase.firestore.FirestoreManager
import com.example.practice.data.persistence.db.AppDatabase
import com.example.practice.domain.repository.home.HomeRepository
import com.example.practice.domain.repository.home.IHomeRepository
import com.example.practice.presentation.home.viewmodel.HomeVMFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun providesRepository(firestoreManager: FirestoreManager): IHomeRepository = HomeRepository(firestoreManager)
    @Provides
    fun providesHomeVMFactory(repo: IHomeRepository): HomeVMFactory = HomeVMFactory(repo)
}