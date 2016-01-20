/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author test
 */
public class JsonArray {

    public static void main(String[] args) {
        try {
            /*    JSONObject obj = new JSONObject();

            JSONArray jsonArray2 = new JSONArray();

            //创建一个JsonArray方法1  
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(0, "ZHULI");
            jsonArray.add(1, "30");
            jsonArray.add(2, "ALI");
jsonArray.add(3, "ALI");
            jsonArray.add(4, "ALI");
            jsonArray.add(5, "ALI");
            jsonArray2.add(jsonArray);
            jsonArray = new JSONArray();
            jsonArray.add(0, "AULI");
            jsonArray.add(1, "30");
            jsonArray.add(2, "ALI");
            jsonArray.add(3, "ALI");
            jsonArray.add(4, "ALI");
            jsonArray.add(5, "ALI");
            jsonArray2.add(jsonArray);

            obj.put("data", jsonArray2);
            System.out.println("jsonArray1：" + obj);*/

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = "2015-11-15 09:00:00";

            Date date = sdf.parse(dateInString);
            System.out.println(date);
            System.out.println(sdf.format(date));

            /*  response.setContentType("application/json");
            request.setCharacterEncoding("UTF-8");
            JSONArray jArray = new JSONArray();
            

            JSONObject arrayObj = new JSONObject();
            arrayObj.put("username", "A");
            arrayObj.put("password", "B");

            jArray.add(0, arrayObj);
            PrintWriter out = response.getWriter();
            out.print(jArray);*/
 /*  response.setContentType("text/html;charset=UTF-8");
            //      
      

        

          
            request.setAttribute("data", data);
             */
 /*RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/main.jsp");
            rd.forward(request, response);*/
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }

}
