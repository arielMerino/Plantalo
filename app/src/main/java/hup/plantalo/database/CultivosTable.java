package hup.plantalo.database;

import android.provider.BaseColumns;

/**
 * Created by eliasPlease on 02-03-2016.
 */
public class CultivosTable {

    public CultivosTable(){

    }

    public static abstract class TableInfoCultivos implements BaseColumns{

        public static final String CULTIVO_NAME = "nombre_cultivo";
        public static final String CULTIVO_DESCRIPTION = "descripcion_cultivo";
        public static final String CULTIVO_IMAGE = "imagen_cultivo";
        public static final String DATABASE_NAME = "plantalo_db";
        public static final String TABLE_NAME = "cultivo_info";
    }
}
