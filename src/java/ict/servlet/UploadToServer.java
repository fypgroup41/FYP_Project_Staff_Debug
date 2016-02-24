package ict.servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author test
 */
public class UploadToServer {

    private static final Log m_log = LogFactory.getLog("MyClassLogger");

    public static int upLoad2Server(String sourceFileUri) {
        String upLoadServerUri = "http://vbacdu.ddns.net:8080/WBS/newjsp.jsp";
        // String [] string = sourceFileUri;
        String fileName = sourceFileUri;
        int serverResponseCode = 0;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        String responseFromServer = "";

        File sourceFile = new File(sourceFileUri);
        if (!sourceFile.isFile()) {

            return 0;
        }
        try { // open a URL connection to the Servlet
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(upLoadServerUri);
            conn = (HttpURLConnection) url.openConnection(); // Open a HTTP  connection to  the URL
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available(); // create a buffer of  maximum size

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            m_log.info("Upload file to server" + "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
            // close streams
            m_log.info("Upload file to server" + fileName + " File is written");
            fileInputStream.close();
            dos.flush();
            dos.close();
        } catch (MalformedURLException ex) {
            //         ex.printStackTrace();
            m_log.error("Upload file to server" + "error: " + ex.getMessage(), ex);
        } catch (Exception e) {
            //      e.printStackTrace();
        }
//this block will give the response of upload link
       /* try {
         BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         String line;
         while ((line = rd.readLine()) != null) {
         m_log.info("Huzza" + "RES Message: " + line);
         }
         rd.close();
         } catch (IOException ioex) {
         m_log.error("Huzza" + "error: " + ioex.getMessage(), ioex);
         }*/
        return serverResponseCode;  // like 200 (Ok)

    } // end upLoad2Server
}
