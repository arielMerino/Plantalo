package hup.plantalo.database;

import android.provider.BaseColumns;

/**
 * Created by eliasPlease on 03-03-2016.
 */
public class ComentariosTipsTable {

    public ComentariosTipsTable(){

    }

    public static abstract class TableInfoComentariosTips implements BaseColumns {

        public static final String CONTENIDO = "contenido";
        public static final String FECHA = "fecha";
        public static final String AUTOR = "autor";
        public static final String TABLE_NAME = "comentariotips_table";
        public static final String CULTIVO = "cultivo";
        public static final String TIPO = "tipo";
        public static final String IMAGEN_AUTOR = "imagen_autor";
    }
}
