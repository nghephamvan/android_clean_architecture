package aki.pvnghe.unittestapi

import aki.pvnghe.data.model.User
import aki.pvnghe.domain.users.fragment.UsersListObserver
import aki.pvnghe.domain.users.fragment.UsersPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(UsersPresenter::class)
class ListObserverTest {
    private lateinit var usersListObserver: UsersListObserver

    @Mock
    lateinit var usersPresenter: UsersPresenter

    @Mock
    lateinit var throwable: Throwable

    @Before
    fun setUp() {
        usersListObserver = UsersListObserver(usersPresenter)
    }

    @Test
    fun `should show article list when observable emits onSuccess`() {
        val list = emptyList<User>()
        usersListObserver.onSuccess(list)
        verify(usersPresenter).showUserList(list)
    }

    @Test
    fun `should print stack trace when observable emits onError`() {
        usersListObserver.onError(throwable)
        verify(throwable).printStackTrace()
    }
}