
import static com.sun.xml.bind.util.CalendarConv.formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author test
 */
public class NewClass {

    public static void main(String[] args) {
        SimpleDateFormat formatter = null;
        java.util.Date utilDate = null;


        try {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            utilDate = formatter.parse("1995-10-10");
            Calendar date = Calendar.getInstance();
            Calendar custom_date = Calendar.getInstance();
            custom_date.setTime(utilDate);
            int year = date.get(Calendar.YEAR) - custom_date.get(Calendar.YEAR);  // 2012
            System.out.println(year + "");

        } catch (Exception ex) {
        }

    }
}
