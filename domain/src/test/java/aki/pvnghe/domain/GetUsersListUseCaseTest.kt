package aki.pvnghe.domain

import aki.pvnghe.data.repository.UserRepository
import aki.pvnghe.domain.model.User
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(UserRepository::class)
class GetUsersListUseCaseTest {
    private val SEARCH_KEY = "nghepham"
    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"

    private lateinit var getUsersListUseCase: GetUsersListUseCase

    @Mock
    lateinit var userRepository: UserRepository

    private val users = listOf(aki.pvnghe.data.model.User(
        login = USER_LOGIN_NGHEPHAMVAN,
        name = "Nghe Pham",
        avatarUrl = "https://avatars3.githubusercontent.com/u/9383651?v=4",
        bio = "Android developer"
    ))

    @Before
    fun setUp() {
        `when`(userRepository.searchUsers(SEARCH_KEY))
            .thenReturn(Single.just(users))

        getUsersListUseCase = GetUsersListUseCase(userRepository, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @After
    fun dispose() {
        getUsersListUseCase.dispose()
    }

    @Test
    fun `should map data`() {
        val observer = TestObserver<List<User>>()
        getUsersListUseCase.execute(observer, SEARCH_KEY)
        observer.assertNoErrors()
        observer.assertComplete()
        assertEquals(User(login = USER_LOGIN_NGHEPHAMVAN, name = "Nghe Pham", avatarUrl = "https://avatars3.githubusercontent.com/u/9383651?v=4", bio = "Android developer"), observer.values()[0][0])
    }
}