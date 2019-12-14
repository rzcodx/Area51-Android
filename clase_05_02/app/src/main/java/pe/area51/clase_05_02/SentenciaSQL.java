package pe.area51.clase_05_02;

import androidx.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;

public class SentenciaSQL {
    public void registrar(Persona persona) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(persona);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public RealmResults<Persona> obtener() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Persona.class).findAll();
    }

//    public void eliminar(String id) {
//        Realm realm = Realm.getDefaultInstance();
//        Persona persona = realm.where(Persona.class)
//                .equalTo("id", id)
//                .findFirst();
//
//        if (persona != null) {
//            persona.deleteFromRealm();
//        }
//    }

    public void eliminar(@NonNull Persona persona) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            persona.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }
}
