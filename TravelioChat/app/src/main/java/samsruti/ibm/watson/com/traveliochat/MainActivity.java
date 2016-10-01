package samsruti.ibm.watson.com.traveliochat;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.version;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> DigitalWardrobe = new ArrayList<>();


        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/sneakers.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/WA.png");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/beach1.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/beach2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/blazer.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/blazer_informal.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/download.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/formalshirt.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/formalshirt2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/hoodie.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/hoodie2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/images.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/jacket.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/jacket2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/jeans.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/jeans2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/party.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/partycasual.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/raincoats.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/shoes.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/shoes2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/sneakers.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/sneakers2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/socks.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/summercasual.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/sweater.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/sweater2.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/ties.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/trousers.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/wedding.jpg");
        DigitalWardrobe.add("https://raw.githubusercontent.com/samsruti/IBM-Watson/master/Digital%20Wardrobe/wedding2.jpg");



        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        TextView userinput = (TextView) findViewById(R.id.usermessage);
        TextView botoutput = (TextView) findViewById(R.id.botMessage);

        ConversationService service = new ConversationService("2016-09-20");
        service.setUsernameAndPassword("dc6841cf-3513-472a-ada5-7eb2c5feb049", "gOSA4F0ShKfF");
        String workspaceId = "9b24e74e-02e9-4ce0-b849-c5a9df7fcf5b";
        MessageRequest newMessage = new MessageRequest.Builder()
                .inputText("Hi")
                .build();

        MessageResponse response = service
                .message(workspaceId, newMessage)
                .execute();
        List<String> val = new ArrayList<String>();
        val.add("Hi");
        val.add("MA");
        val.add("Saturday");
        val.add("1 week");
        val.add("meeting");
        for (int i=0;i<val.size();i++){

            MessageRequest newMessage1 = new MessageRequest.Builder()
                    .inputText(val.get(i))
                    // Replace with the context obtained from the initial request
                    .context(response.getContext())
                    .build();

            response = service
                    .message(workspaceId, newMessage1)
                    .execute();

            Log.v("Input: ", response.getInputText());
            Log.v("Output:", response.getOutput().toString());
        }
        String season;
        Calendar cal = Calendar.getInstance();
        String monthOfGoing = new SimpleDateFormat("MMM").format(cal.getTime());

        if (monthOfGoing.equalsIgnoreCase("march")||monthOfGoing.equalsIgnoreCase("april") ||monthOfGoing.equalsIgnoreCase("may")){
            season = "spring";
        }
        else if (monthOfGoing.equalsIgnoreCase("june")||monthOfGoing.equalsIgnoreCase("july") ||monthOfGoing.equalsIgnoreCase("august")){
            season = "summer";
        }
        else {
            season = "rainy";
        }

//        API KEY DETAILS IMAGGA
//
        String API_Secret = "786805a0533dc6eb1a6360ae8434ae92";
        String API_Key = "acc_1463614a40dd0e3";

        for (int i = 0; i < DigitalWardrobe.size();i++){
            Log.v("Classify an image:","Images processing");
            String url1 = "https://gateway-a.watsonplatform.net/visual-recognition/api/v3/classify?api_key="+"6a55844a9096c56d424a8beeeaf5f10a5e299b8d"+"&url="+DigitalWardrobe.get(i)+"&version=2016-05-20";
            try{

                URL url = new URL(url1);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                    for (String line; (line = reader.readLine()) != null;) {
                        Log.v("Results", line);
                    }
                }
            }
            catch (Exception E){

            }


        }



    }

}
