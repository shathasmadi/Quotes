/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Quotes;



import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class App {
    public String getGreeting() {

        return "Hello World!";
    }
   //the demo help me in solve this lab
    public static void main(String[] args) {
        try {
            URL url = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            int status = connect.getResponseCode();

            if(status == 200){
                InputStream input = connect.getInputStream();
                InputStreamReader inputRead = new InputStreamReader(input);
                BufferedReader bufRead = new BufferedReader(inputRead);
                String line = bufRead.readLine();

                System.out.println(line);

                bufRead.close();
            } else{
                System.out.println("locally");
                FileReader jsonFile = new FileReader("recentquotes.json");

                ArrayList<Quote> q= readJsonFile(jsonFile);
                int r = (int) (Math.random() * (q.size()));
                System.out.println(q.get(r).toString());
            }

            connect.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Quote> readJsonFile(FileReader jsonFiles){
        Gson g =new  Gson();

        BufferedReader reader= new BufferedReader(jsonFiles);

        ArrayList<Quote> q= g.fromJson(reader,new TypeToken<ArrayList<Quote>>() {}.getType());

        try{
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return q;
    }

}



