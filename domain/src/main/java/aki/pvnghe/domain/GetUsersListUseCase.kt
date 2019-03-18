package aki.pvnghe.domain

import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.domain.model.User
import aki.pvnghe.domain.usecase.UseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetArticlesListUseCase @Inject constructor(private val userRepository: UserRepository,
                                                 subscribeScheduler: Scheduler,
                                                 postExecutionScheduler: Scheduler
) : UseCase<List<User>, Unit>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(searchTerm: String?, params: Unit?): Single<List<User>> = userRepository.searchUsers(searchTerm)
        .map {
            it.map { User(it.login, it.name, it.avatarUrl, it.bio) }
        }
}
