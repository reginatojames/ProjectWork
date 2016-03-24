package com.example.reginatojames.projectwork;

import android.content.res.AssetFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*InputStream inputStream = getResources().openRawResource(R.raw.stats);
        List scoreList = csvFile.read();*/

        parser();
    }

    public void parser(){
        try{
            //17 ; 18 keywords

            InputStream file = getResources().openRawResource(R.raw.attiv_commerc);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line = br.readLine();	//read header

            String[] element = new String[18];	//string array to store data of a line

            int i = 0;
            int j = 0;

            Map<Indirizzo, ArrayList<Integer>> addresses = new HashMap<Indirizzo, ArrayList<Integer>>();
            Indirizzo address = null;

            StringBuilder builder = new StringBuilder();

            ArrayList<Integer> sameAddress = new ArrayList<Integer>();

            while((line = br.readLine()) != null){
                //loop per prendere la linea completa di ogni record

                while(countOccurrences(line, ';') < 17){
                    line = line.concat(br.readLine());
                }

                //array che salva i valori del record
                element = line.split(";");
                j++;
                Log.d("TAG",element[2]+element[3]+element[4]);
                //System.out.println(j+": "+element[2]+element[3]+element[4]);

                //se l'indirizzo è valido, crea oggetto
                if(element[2].length() > 0 && element[3].length() > 0 && element[4].length() > 0){
                    address = new Indirizzo(element[2], element[3], element[4], element[5]);
                    ++i;
                    //prende lista di indirizzi già esistenti
                    ArrayList<Integer> temp = addresses.get(address);

                    //controlla se esiste la lista, se sì
                    if(temp == null){
                        sameAddress = new ArrayList<Integer>();
                        sameAddress.add(i);
                        addresses.put(address, sameAddress);
                    }
                    else {
                        temp.add(i);
                        addresses.put(address, temp);
                    }
                }
            }
            System.out.println("Numero record: " + (i-1));

           /*FileWriter fw = new FileWriter("nomefileout.csv");
            PrintWriter pw = new PrintWriter(fw);
            pw.println("id1;lat;lng;google address");
            int k=0;

            GeoApiContext context = null;
            GeocodingResult[] results = null;

            for(Address tAddress: addresses.keySet()) {
                System.out.println(tAddress.toString());
                pw.println(tAddress);
                if(k<10){
                    context = new GeoApiContext().setApiKey("");
                    results =  GeocodingApi.geocode(context,
                            "Milano " + tAddress.toString()).await();
                    System.out.println(results[0].formattedAddress);
                }
                ++k;
            }

            pw.close();*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int countOccurrences(String str, char c){
        int i = 0;
        for(int j = 0; j<str.length(); ++j){
            if(str.charAt(j) == c)
                ++i;
        }
        return i;
    }
}
