package pl.gungnir.linkmanager.uitl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.gungnir.linkmanager.domain.useCase.GetLinkUseCase
import pl.gungnir.linkmanager.network.DatabaseRepo
import pl.gungnir.linkmanager.network.DatabaseRepoImpl
import pl.gungnir.linkmanager.network.FirebaseDatabaseRepo
import pl.gungnir.linkmanager.network.FirebaseDatabaseRepoImpl
import pl.gungnir.linkmanager.ui.history.adapter.LinkAdapter

@Module
@InstallIn(ActivityComponent::class)
class DatabasesRepoModule {

    @Provides
    fun provideFirebaseDatabaseRepo(): FirebaseDatabaseRepo {
        return FirebaseDatabaseRepoImpl()
    }

    @Provides
    fun provideDatabaseRepo(
        firebaseDatabaseRepo: FirebaseDatabaseRepo
    ): DatabaseRepo {
        return DatabaseRepoImpl(
            firebaseDatabaseRepo
        )
    }

    @Provides
    fun provideLinkAdapter(): LinkAdapter {
        return LinkAdapter()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetLinkUseCase(): GetLinkUseCase {
        return GetLinkUseCase(DatabaseRepoImpl(FirebaseDatabaseRepoImpl()))
    }
}