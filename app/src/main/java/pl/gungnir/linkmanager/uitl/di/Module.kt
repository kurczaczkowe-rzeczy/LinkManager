package pl.gungnir.linkmanager.uitl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import pl.gungnir.linkmanager.network.DatabaseRepo
import pl.gungnir.linkmanager.network.DatabaseRepoImpl
import pl.gungnir.linkmanager.network.FirebaseDatabaseRepo
import pl.gungnir.linkmanager.network.FirebaseDatabaseRepoImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class DatabasesRepoModule {

    @Binds
    abstract fun bindDatabaseRepo(
        databaseRepoImpl: DatabaseRepoImpl
    ): DatabaseRepo

    @Binds
    abstract fun bindFirebaseDatabaseRepo(
        firebaseDatabaseRepo: FirebaseDatabaseRepoImpl
    ): FirebaseDatabaseRepo
}