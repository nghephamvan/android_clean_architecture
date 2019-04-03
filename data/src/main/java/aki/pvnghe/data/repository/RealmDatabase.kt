package aki.pvnghe.data.repository

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration


object RealmDatabase {
    fun initializeRealm(applicationContext: Context) {
        // Initialize Realm
        Realm.init(applicationContext);
        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        val config = RealmConfiguration.Builder()
                //.name("myrealm.realm")
                .encryptionKey(ByteArray(64))
                .schemaVersion(1)
                //.migration(MyMigration())
                .build()
        Realm.setDefaultConfiguration(config)
    }

    fun getRealmInstance(): Realm = Realm.getDefaultInstance()
}