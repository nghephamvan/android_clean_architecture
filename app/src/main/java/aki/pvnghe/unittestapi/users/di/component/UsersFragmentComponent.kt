package aki.pvnghe.unittestapi.users.di.component

import aki.pvnghe.mvp.di.scope.PerFragment
import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.users.UsersFragment
import aki.pvnghe.unittestapi.users.di.module.FragmentModule
import dagger.Component

@PerFragment
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface UsersFragmentComponent {
    fun inject(usersFragment: UsersFragment)
}