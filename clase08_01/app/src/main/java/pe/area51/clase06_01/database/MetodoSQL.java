package pe.area51.clase06_01.database;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import pe.area51.clase06_01.database.posts.PostEntidad;
import pe.area51.clase06_01.database.usuarios.UsuarioEntidad;

public class MetodoSQL {
    public boolean registrarUsuario(UsuarioEntidad entidad) {
        Realm realm = Realm.getDefaultInstance();

        try {
            realm.beginTransaction();
            realm.copyToRealm(entidad);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
        }

        return  false;
    }

    public RealmResults<UsuarioEntidad> obtenerUsuarios() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class).findAll();
    }

    public RealmResults<UsuarioEntidad> obtenerUsuarioPorNombre(String nombre) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(UsuarioEntidad.class)
                .contains("name", nombre, Case.INSENSITIVE)
                .findAll();
    }

    public void eliminarUsuarios() {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            realm.where(UsuarioEntidad.class).findAll().deleteAllFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public boolean registrarPost(PostEntidad entidad) {
        Realm realm = Realm.getDefaultInstance();

        try {
            realm.beginTransaction();
            realm.copyToRealm(entidad);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            realm.cancelTransaction();
        }

        return  false;
    }

    public RealmResults<PostEntidad> obtenerPosts(int userId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(PostEntidad.class)
                .equalTo("userId", userId)
                .findAll();
    }
}
