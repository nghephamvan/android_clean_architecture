package aki.pvnghe.unittestapi

import aki.pvnghe.data.service.getusers.SearchUsersService
import aki.pvnghe.domain.model.User
import aki.pvnghe.domain.users.fragment.UsersListObserver
import aki.pvnghe.domain.users.fragment.UsersPresenter
import aki.pvnghe.domain.users.fragment.UsersView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(SearchUsersService::class)
class UsersPresenterTest {
    private lateinit var presenter: UsersPresenter

    @Suppress("UNKNOWN")
    private fun <T> any(type: Class<T>): T {
        Mockito.any<T>(type)
        return null as T
    }

    @Mock
    lateinit var searchUsersService: SearchUsersService

    @Mock
    lateinit var view: UsersView

    @Before
    fun setUp() {
        presenter = UsersPresenter(searchUsersService)
        presenter.attachView(view)
    }

    @Test
    fun `should init view`() {
        presenter.initialise()
        verify(view).initialiseView()
    }

    @Test
    fun `should dispose subscription`() {
        presenter.disposeSubscriptions()
        verify(searchUsersService).dispose()
    }

    @Test
    fun `should execute usecase when initialise is invoked`() {
        presenter.initialise()
        verify(searchUsersService).execute(any(UsersListObserver::class.java), anyString())
    }

    @Test
    fun `should set articles list to view`() {
        val list = emptyList<User>()
        presenter.showUserList(list)
        verify(view).showUserList(list)
    }
}