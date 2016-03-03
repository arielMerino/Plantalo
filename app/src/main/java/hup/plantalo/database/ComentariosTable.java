package hup.plantalo.database;

import android.provider.BaseColumns;

/**
 * Created by eliasPlease on 03-03-2016.
 */
public class ComentariosTable {

    public ComentariosTable(){

    }

    public static abstract class TableInfoComentarios implements BaseColumns {

        public static final String COMENTARIO = "comentario_texto";
        public static final String FECHA = "fecha_comentario";
        public static final String AUTOR_COMENTARIO = "autor_comentario";
        public static final String TABLE_NAME = "comentario_table";
    }
}
