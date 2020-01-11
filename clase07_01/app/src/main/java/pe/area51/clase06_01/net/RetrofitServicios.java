package pe.area51.clase06_01.net;

import java.util.ArrayList;

import pe.area51.clase06_01.net.response.album.Album;
import pe.area51.clase06_01.net.response.comentario.Comentario;
import pe.area51.clase06_01.net.response.foto.Foto;
import pe.area51.clase06_01.net.response.post.Post;
import pe.area51.clase06_01.net.response.usuario.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitServicios {
    @GET("users")
    Call<ArrayList<Usuario>> obtenerUsuarios();

    @GET("posts")
    Call<ArrayList<Post>> obtenerPostPorUsuario(@Query("userId") int userId);

    @GET("posts/{id}/comments")
    Call<ArrayList<Comentario>> obtenerComentariosPorPost(@Path("id") int id);

    @GET("albums")
    Call<ArrayList<Album>> obtenerAlbumPorUsuario(@Query("userId") int userId);

    @GET("albums/{id}/photos")
    Call<ArrayList<Foto>> obtenerfotosPorAlbum(@Path("id") int idAlbum);
}
