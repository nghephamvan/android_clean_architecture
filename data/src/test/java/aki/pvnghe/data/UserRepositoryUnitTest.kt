package aki.pvnghe.data

import aki.pvnghe.data.model.User
import aki.pvnghe.data.model.UsersList
import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.data.service.GithubUserService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


@RunWith(PowerMockRunner::class)
@PrepareForTest(UsersList::class, User::class)
class UserRepositoryUnitTest {

    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"
    private val USER_LOGIN_2_NGHEPHAM = "nghepham97"


    lateinit var userRepository: UserRepository

    @Mock
    lateinit var userService: GithubUserService

    @Mock
    lateinit var usersList: UsersList

    @Mock
    lateinit var user: User

    lateinit var users: List<User>

    @Before
    fun setUp() {
        users = listOf(user)

        `when`(usersList.items)
            .thenReturn(users)
        `when`(userService.searchGithubUsers(USER_LOGIN_NGHEPHAMVAN))
            .thenReturn(Single.just(usersList))

        userRepository = UserRepository(userService)
    }

    @Test
    fun `should map users field to the root level`() {
        val observer = TestObserver<List<User>>()
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN)
            .subscribe(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        observer.assertValueAt(0, users)
    }
}