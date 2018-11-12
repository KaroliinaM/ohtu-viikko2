/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;

/**
 *
 * @author kape
 */
public class MyCourses {

    private String name;
    private Course course;
    private int allHours;
    private int allExercises;
    private int allDoneExercises;
    
    private int maxExercises=0;
    private int doneExercises=0;
    private int sumHours=0;
    ArrayList<Submission> submission;
    public MyCourses(String name, Course[] courses, Submission[] submissions ) {
        submission=new ArrayList<Submission>();
        this.name=name;
        for(Course c: courses) {
            if(c.getName().equals(name)) {
                this.course=c;
            }
        }
        for(Submission s:submissions) {
            if(s.getCourseName().equals(name)) {
                submission.add(s);
                this.doneExercises+=s.getExerciseCount();
                this.sumHours+=s.getHours();
            }
        }
        
        
        
        
//        Ohjelmistotuotanto syksy 2018
// 
//viikko 1:
// tehtyjä tehtäviä 15/17 aikaa kului 20 tehdyt tehtävät: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
//viikko 2:
// tehtyjä tehtäviä 11/13 aikaa kului 5 tehdyt tehtävät: 1, 2, 3, 4, 6, 7, 8, 10, 11, 12, 13
//
//yhteensä: 26/30 tehtävää 25 tuntia
        
    }
    public void setAllHours(int allHours) {
        this.allHours=allHours;
    }
    public void setAllExercises(int allExercises) {
        this.allExercises=allExercises;
    }
    public void setAllDoneExercises(int doneExercises) {
        this.allDoneExercises=doneExercises;
    }
    @Override
    public String toString() {
        String palautettava="\n\n"+this.course.getFullName()+ " " + this.course.getTerm()+ " "+this.course.getYear() +"\n\n";
        for(Submission s: submission) {
            palautettava+="Viikko " + s.getWeek()+"\n";
            palautettava+="Tehtyjä tehtäviä " + s.getExerciseCount()+"/"+this.course.getExercises(s.getWeek())+ " aikaa kului " + s.getHours();
            palautettava+=" tehdyt tehtävät " + s.getExercises();
            palautettava+="\n";
        }
        palautettava+="yhteensä " + this.doneExercises+"/"+course.getExerciseCount()+" "+ this.sumHours+" tuntia";
        palautettava+="\nkurssilla yhteensä "+this.allExercises+" palautusta, palautettuja tehtäviä "+this.allDoneExercises+" kpl, aikaa käytetty yhteensä "+this.allHours+" tuntia";
        return palautettava;
        
    }
    
}

    

