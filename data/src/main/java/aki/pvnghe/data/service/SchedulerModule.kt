package aki.pvnghe.data.service

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulerModule {
    companion object {
        const val RX_IO_SCHEDULER = "ioScheduler"
        const val RX_MAIN_THREAD_SCHEDULER = "mainThreadScheduler"
    }

    @Provides
    @Named(RX_IO_SCHEDULER)
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Named(RX_MAIN_THREAD_SCHEDULER)
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()
}