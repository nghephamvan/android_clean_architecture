package aki.pvnghe.domain.usecase

import aki.pvnghe.data.service.user.UserService
import aki.pvnghe.domain.model.User
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val userService: UserService,
                                              subscribeScheduler: Scheduler,
                                              postExecutionScheduler: Scheduler
) : UseCase<List<User>, String>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: String?): Single<List<User>> = userService.searchUsers(params)
    .map {
        it.map { User(it.login, it.name, it.avatarUrl, it.bio) }
    }

    /* var test: ByteArray? = null
   init {
       test = ByteArray(1024*1024*70)
   }

   fun test() {
       if (test != null) {

       }
   }*/
}
