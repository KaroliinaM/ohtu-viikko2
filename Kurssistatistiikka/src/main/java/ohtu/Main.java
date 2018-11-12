package ohtu;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.IOException;
import java.util.ArrayList;
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
        Gson mapper2 = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] classes = mapper2.fromJson(coursebodyText, Course[].class);
        int exerciseCount=0;
        int hours=0;
        ArrayList<MyCourses> courseList=new ArrayList<MyCourses>();
        ArrayList<String> courseNames=new ArrayList<>();
        for (Submission submission : subs) {
            if(!courseNames.contains(submission.getCourseName())) {
                courseNames.add(submission.getCourseName());
            }
        }
        for(String s: courseNames) {
            MyCourses c=new MyCourses(s, classes, subs);
            
            String statsUrl="https://studies.cs.helsinki.fi/courses/"+s+"/stats";
            String statsResponse=Request.Get(statsUrl).execute().returnContent().asString();
            JsonParser parser = new JsonParser();
            JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
            //kurssilla yhteensä 163 palautusta, palautettuja tehtäviä 2517 kpl, aikaa käytetty yhteensä 1100 tuntia
            int palautuksetYhteensa=0;
            int tehtavatYhteensa=0;
            int aikaYhteensa=0;
            for(int i=0; i<parsittuData.size(); i++) {
                int n=i+1;
                aikaYhteensa+=parsittuData.get(n+"").getAsJsonObject().get("hour_total").getAsInt();
                palautuksetYhteensa+=parsittuData.get(n+"").getAsJsonObject().get("exercise_total").getAsInt();
                JsonArray assignments=parsittuData.get(n+"").getAsJsonObject().get("exercises").getAsJsonArray();
                tehtavatYhteensa+=assignments.size();
//                System.out.println(parsittuData.get(n+"").getAsJsonObject().get("hours").getAsJsonArray().get(1));
            }
            c.setAllExercises(tehtavatYhteensa);
            c.setAllHours(aikaYhteensa);
            c.setAllDoneExercises(palautuksetYhteensa);
          
            courseList.add(c);
//            
//            System.out.println(aikaYhteensa);
//            System.out.println(palautuksetYhteensa);
//            System.out.println(tehtavatYhteensa+"hei");
//            System.out.println(parsittuData.get("1"));
//            System.out.println(parsittuData.get("1").getAsJsonObject().get("students").getAsInt());
        }
        
        
        
        
        
        //System.out.println("Oliot:");
        System.out.println("Opiskelijanro "+ studentNr +"\n\n");
        for(MyCourses m: courseList) {
            System.out.println(m);
        }
//        for (Submission submission : subs) {
//            System.out.println(submission);
//            exerciseCount+=submission.getExerciseCount();
//            hours+=submission.getHours();
//        }
//        System.out.println("yhteensä: "+exerciseCount+" tehtävää " + hours+ " tuntia.");

    }
}
