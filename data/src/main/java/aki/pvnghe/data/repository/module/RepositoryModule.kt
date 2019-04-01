package aki.pvnghe.data.repository.module

import aki.pvnghe.data.repository.UserRepositoryImpl
import aki.pvnghe.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideUserRepository(): UserRepository = UserRepositoryImpl()
}