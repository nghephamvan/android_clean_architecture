package aki.pvnghe.data

import aki.pvnghe.data.model.User
import aki.pvnghe.data.model.UsersList
import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.data.service.GithubUserService
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.powermock.core.classloader.annotations.PrepareForTest

@PrepareForTest(User::class, UsersList::class)
class UserRepositoryUnitTest {

    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"
    private val USER_LOGIN_2_NGHEPHAM = "nghepham97"

    @Mock
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

        githubUserList().items?.map { users }


        `when`(userList.items).thenReturn(users)
        `when`(githubUserService.searchGithubUsers(USER_LOGIN_NGHEPHAMVAN)).thenReturn(Single.just(userList))

        userRepository = UserRepository(githubUserService)
    }

    @Test
    fun testSearchUser() {
        val observer = TestObserver<List<User>>()
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN)
            .subscribe(observer)
        observer.awaitTerminalEvent()
        observer.assertNoErrors()
        observer.assertComplete()
        observer.assertValueAt(0, users)

        val events : List<List<User>>? = observer.events as List<List<User>>?
        val users = events?.get(0)

        Assert.assertEquals(USER_LOGIN_NGHEPHAMVAN, users?.get(0)?.login)
        Assert.assertEquals(USER_LOGIN_2_NGHEPHAM, users?.get(1)?.login)
        verify(githubUserService).searchGithubUsers(USER_LOGIN_NGHEPHAMVAN)
        verify(githubUserService).getUser(USER_LOGIN_NGHEPHAMVAN)
        verify(githubUserService).getUser(USER_LOGIN_2_NGHEPHAM)
    }

    private fun githubUserList(): UsersList {
        val user = User(USER_LOGIN_NGHEPHAMVAN)

        val user2 = User(USER_LOGIN_2_NGHEPHAM)

        val githubUsers = java.util.ArrayList<User>()
        githubUsers.add(user)
        githubUsers.add(user2)
        return UsersList(items = githubUsers)
    }

    private fun user1FullDetails(): User {
        return User(
            login = USER_LOGIN_NGHEPHAMVAN,
            name = "Nghe Pham",
            avatarUrl = "https://avatars3.githubusercontent.com/u/9383651?v=4",
            bio = "Android developer"
        )
    }

    private fun user2FullDetails(): User {
        return User(
            login = USER_LOGIN_2_NGHEPHAM,
            name = null,
            avatarUrl = "https://avatars1.githubusercontent.com/u/32038000?v=4",
            bio = null
        )
    }
}