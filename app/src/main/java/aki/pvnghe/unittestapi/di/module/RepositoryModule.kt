package aki.pvnghe.unittestapi.di.module

import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.data.service.GithubUserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
  @Provides
  @Singleton
  internal fun provideUserRepository(githubUserService: GithubUserService): UserRepository = UserRepository(githubUserService)
}