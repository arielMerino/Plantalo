package hup.plantalo.database;

import android.provider.BaseColumns;

public class MisCultivosTable {

    public MisCultivosTable(){

    }

    public static abstract class TableMisCultivosInfo implements BaseColumns{
        public static final String CULTIVO = "nombre_cultivo";
        public static final String TABLE_NAME = "mis_cultivos_table";
    }
}
