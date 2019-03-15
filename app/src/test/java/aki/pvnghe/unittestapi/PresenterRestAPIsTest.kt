package aki.pvnghe.unittestapi

import aki.pvnghe.unittestapi.main.presenter.MainPresenter
import aki.pvnghe.unittestapi.main.view.SearchView
import aki.pvnghe.unittestapi.model.User
import aki.pvnghe.unittestapi.model.UsersList
import aki.pvnghe.unittestapi.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import rx.Observable
import rx.schedulers.Schedulers
import java.util.ArrayList

open class PresenterRestAPIsTest {

    private val USER_LOGIN_NGHEPHAMVAN = "nghephamvan"
    private val USER_LOGIN_2_NGHEPHAM = "nghepham97"

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var view: SearchView

    internal lateinit var mainPresenter: MainPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(userRepository, Schedulers.immediate(), Schedulers.immediate())
        mainPresenter.attachView(view)
    }

    @Test
    fun search_ValidSearchTerm_ReturnsResults() {
        val userList = getDummyUserList()
        `when`(userRepository.searchUsers(anyString())).thenReturn(Observable.just<List<User>>(userList.getItems()))

        mainPresenter.onSearch("nghephamvan")

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showSearchResults(userList.getItems())
        verify(view, never()).showError(anyString())
    }

    @Test(expected = RuntimeException::class)
    fun search_NotAttached_ThrowsMvpException() {
        mainPresenter.detachView()

        mainPresenter.onSearch("test")

        verify(view, never()).showLoading()
//        verify(view, never()).showSearchResults( anyList() as List<User>)
    }

    private fun getDummyUserList(): UsersList {
        val githubUsers = ArrayList<User>()
        githubUsers.add(user1FullDetails())
        githubUsers.add(user2FullDetails())
        return UsersList(githubUsers)
    }

    private fun user1FullDetails(): User {
        return User(USER_LOGIN_NGHEPHAMVAN, "Nghe Pham", "https://avatars3.githubusercontent.com/u/9383651?v=4", "Android developer")
    }

    private fun user2FullDetails(): User {
        return User(USER_LOGIN_2_NGHEPHAM, null, "https://avatars1.githubusercontent.com/u/32038000?v=4", null)
    }
}