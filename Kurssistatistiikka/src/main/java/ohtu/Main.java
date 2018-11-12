package ohtu;

import com.google.gson.Gson;
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
            courseList.add(c);
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
