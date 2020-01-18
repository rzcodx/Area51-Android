package pe.area51.clase06_01;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .name("clase_07_01")
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
