package ohtu;

import java.util.ArrayList;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }
    
    public void setHours(int hours) {
        this.hours=hours;
    }
    
    public void setExercises(int[] exercises){
        this.exercises=exercises;
    }
    public void setCourseName(String name) {
        this.course=name;
    }

    public int getWeek() {
        return week;
    }
    public int getHours() {
        return this.hours;
    }
    public int getExerciseCount() {
        return this.exercises.length;
    }
    public String getCourseName() {
        return this.course;
    }

    @Override
    public String toString() {
        String palautettava=course+", viikko "+week+", tehtyjä tehtäviä yhteensä " +  exercises.length+ " aikaa kului " +hours+ " tuntia tehdyt tehtävät :";
        for(int i=0; i<exercises.length; i++) {
            palautettava+=exercises[i]+", ";
        }
        return palautettava;
    }
    
}