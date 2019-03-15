package aki.pvnghe.data

import aki.pvnghe.data.model.User
import aki.pvnghe.data.model.UsersList
import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.data.repository.UserRepositoryImpl
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
@PrepareForTest(User::class, UsersList::class)
class UserRepositoryUnitTest {

    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"
    private val USER_LOGIN_2_NGHEPHAM = "nghepham97"

    lateinit var userRepository: UserRepository

    @Mock
    lateinit var githubUserService: GithubUserService

    @Mock
    lateinit var user: User

    @Mock
    lateinit var userList: UsersList

    lateinit var users : ArrayList<User>

    @Before
    fun setUp() {
        users = arrayListOf(user)

        `when`(userList.items).thenReturn(users)
        `when`(githubUserService.searchGithubUsers(USER_LOGIN_NGHEPHAMVAN)).thenReturn(Single.just(userList))

        userRepository = UserRepositoryImpl(githubUserService)
    }

    @Test
    fun testSearchUser() {
        val observer = TestObserver<List<User>>()
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN)
            .subscribe(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        observer.assertValueAt(0, users)
    }
}