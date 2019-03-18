package aki.pvnghe.unittestapi.users.di.component

import aki.pvnghe.mvp.di.scope.PerActivity
import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.users.MainActivity
import aki.pvnghe.unittestapi.users.di.module.UserModule
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [UserModule::class])
interface UsersComponent {
    fun inject(mainActivity: MainActivity)
}