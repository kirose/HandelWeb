package mx.handel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UtilFormat {
    private static final Map<String, SimpleDateFormat> FORMATS = new HashMap<>();
    public static final Locale LOCALE_es_MX = new Locale("es", "MX");

    public static String date(String strformat, Date fecha)
    {
        String strfecha = null;
        try{
            SimpleDateFormat format  = FORMATS.containsKey(strformat) ? FORMATS.get(strformat) : new SimpleDateFormat(strformat, LOCALE_es_MX);
            strfecha = format.format(fecha);

            if (!FORMATS.containsKey(strformat)){
                FORMATS.put(strformat, format);
            }
        } catch (Exception ex){}
        return strfecha;
    }
}
