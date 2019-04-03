package aki.pvnghe.data.repository

import aki.pvnghe.data.model.User
import android.util.Log

class UserRepositoryImpl : UserRepository {

    companion object {
        private const val TAG = "UserRepositoryImpl"
    }

    override fun deleteAllUsers(callback: () -> Unit) {
        val transaction = RealmDatabase.getRealmInstance()
        try {
            //NOTE: java.lang.IllegalStateException: Callback cannot be delivered on current thread. Realm cannot be automatically updated on a thread without a looper.
            //This Error will happen if the app calls executeTransactionAsync when device's screen is off
            transaction.executeTransactionAsync({
                it.delete(User::class.java)
            }, {
                callback.invoke()
            }, {
                Log.d(TAG, "deleteAllUsers()", it)
                callback.invoke()
            })
        } catch (e: Exception) {
            Log.d(TAG, "deleteAllUsers()", e)
        } finally {
            transaction.close()
        }
    }

    override fun insertUsersList(usersList: List<User>?, callback: () -> Unit) {
        deleteAllUsers() {
            usersList?.let {
                val transaction = RealmDatabase.getRealmInstance()
                try {
                    transaction.executeTransactionAsync({
                        it.insert(usersList)
                    }, {
                        callback.invoke()
                    }, {
                        Log.d(TAG, "insertUsersList()", it)
                        callback.invoke()
                    })
                } catch (e: Exception) {
                    Log.d(TAG, "insertUsersList()", e)
                } finally {
                    transaction.close()
                }
            }
        }
    }


}