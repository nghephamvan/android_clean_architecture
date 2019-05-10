package aki.pvnghe.data

import aki.pvnghe.data.model.User
import aki.pvnghe.data.service.getusers.GetUsersResponse
import aki.pvnghe.data.service.getusers.UserApi
import aki.pvnghe.data.service.getusers.UserService
import aki.pvnghe.data.service.getusers.UserServiceImpl
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
@PrepareForTest(GetUsersResponse::class, User::class)
class UserServiceUnitTest {

    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"
    private val USER_LOGIN_2_NGHEPHAM = "nghepham97"


    lateinit var userService: UserService

    @Mock
    lateinit var userApi: UserApi

    @Mock
    lateinit var usersList: GetUsersResponse

    @Mock
    lateinit var user: User

    lateinit var users: List<User>

    @Before
    fun setUp() {
        users = listOf(user)

        `when`(usersList.items).thenReturn(users)
        `when`(userApi.searchGithubUsers(USER_LOGIN_NGHEPHAMVAN)).thenReturn(Single.just(usersList))
        `when`(userApi.getUser(USER_LOGIN_NGHEPHAMVAN)).thenReturn(Single.just(user))

        userService = UserServiceImpl(userApi)
    }

    @Test
    fun `should map users field to the root level`() {
        val observer = TestObserver<List<User>>()
        userService.searchUsers(USER_LOGIN_NGHEPHAMVAN)
                .subscribe(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        observer.assertValueAt(0, users)
    }

    @Test
    fun `should map user`() {
        val observer = TestObserver<User>()
        userService.getUser(USER_LOGIN_NGHEPHAMVAN)
                .subscribe(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        observer.assertValue(user)
    }
}