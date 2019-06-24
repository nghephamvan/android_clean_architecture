package aki.pvnghe.unittestapi

import aki.pvnghe.domain.users.activity.MainPresenter
import aki.pvnghe.domain.users.activity.MainView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MainPresenterTest {
    private lateinit var presenter: MainPresenter

    @Suppress("UNKNOWN_MAIN")
    private fun <T> any(type: Class<T>): T {
        Mockito.any<T>(type)
        return null as T
    }

    @Mock lateinit var mainView: MainView

    @Before
    fun setUp() {
        presenter = MainPresenter()
        presenter.attachView(mainView)
    }

    @Test
    fun`should init view`() {
        presenter.initialise()
        verify(mainView).initialiseView()
    }

    @Test
    fun`coverage presenter`() {
        presenter.disposeSubscriptions()
        verify(mainView)
    }


}