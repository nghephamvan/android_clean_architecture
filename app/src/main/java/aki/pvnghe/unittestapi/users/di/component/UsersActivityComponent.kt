package aki.pvnghe.unittestapi.users.di.component

import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.users.MainActivity
import aki.pvnghe.unittestapi.users.di.module.ActivityModule
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface UsersActivityComponent {
    fun inject(mainActivity: MainActivity)
}