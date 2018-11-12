package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String kurssiUrl="https://studies.cs.helsinki.fi/courses/courseinfo";
        String coursebodyText = Request.Get(kurssiUrl).execute().returnContent().asString();

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( coursebodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        int exerciseCount=0;
        int hours=0;
        
        
        System.out.println("Oliot:");
                    System.out.println("Opiskelijanro "+ studentNr +"\n\n");
        for (Submission submission : subs) {
            System.out.println(submission);
            exerciseCount+=submission.getExerciseCount();
            hours+=submission.getHours();
        }
        System.out.println("yhteensä: "+exerciseCount+" tehtävää " + hours+ " tuntia.");

    }
}
