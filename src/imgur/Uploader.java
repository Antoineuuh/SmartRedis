package imgur;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.*;

public class Uploader {

    public static String UPLOAD_API_URL = "https://api.imgur.com/3/image";
    public static int MAX_UPLOAD_ATTEMPTS = 3;

    private static String CLIENT_ID = "WRONG_CLIENT_ID_LOL";

    public static String UPLOAD(File file) {
        
        HttpURLConnection CONN = GET_HTTP_CONNECTION(UPLOAD_API_URL);
        WRITE_TO_CONNECTION(CONN, "image=" + TO_BASE_64(file));
        return GET_RESPONSE(CONN);
    }

    private static String GET_RESPONSE(HttpURLConnection conn) {

        StringBuilder str = new StringBuilder();
        BufferedReader reader;
        try
        {
            if (conn.getResponseCode() != 200)
            {
                return "";
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                str.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (str.toString().equals(""))
        {
            return "";
        }
        return str.toString();
    }

    private static String TO_BASE_64(File file) {

        try {
            byte[] b = new byte[(int) file.length()];
            FileInputStream fs = new FileInputStream(file);
            fs.read(b);
            fs.close();
            return URLEncoder.encode(DatatypeConverter.printBase64Binary(b), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static HttpURLConnection GET_HTTP_CONNECTION(String URL) {

        HttpURLConnection CONN;

        try {
            CONN = (HttpURLConnection) new URL(URL).openConnection();
            CONN.setDoInput(true);
            CONN.setDoOutput(true);
            CONN.setRequestMethod("POST");
            CONN.setRequestProperty("Authorization", "Client-ID " + CLIENT_ID);
            CONN.setReadTimeout(100000);
            CONN.connect();
            return CONN;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static void WRITE_TO_CONNECTION(HttpURLConnection CONN, String MESSAGE) {

        OutputStreamWriter WRITER;

        try
        {
            WRITER = new OutputStreamWriter(CONN.getOutputStream());
            WRITER.write(MESSAGE);
            WRITER.flush();
            WRITER.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
