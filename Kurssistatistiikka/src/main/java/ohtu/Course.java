/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

/**
 *
 * @author kape
 */
public class Course {
    private String name;
    private String term;
    private int year;
    private String fullName;
    private int[] exercises;
    
    public void setName(String name) {
        this.name=name;
    }
    public void setTerm(String term) {
        this.term=term;
    }
    public void setYear(int year) {
        this.year=year;
    }
    
    public void setFullName(String fullName) {
        this.fullName=fullName;
    }
    
    public void setExcercisses(int[] exercises) {
        this.exercises=exercises;
    }
    public String getName() {
        return this.name;
    }
    public String getFullName() {
        return this.fullName;
    }
    public String getTerm() {
        return this.term;
    }
    public int getYear() {
        return this.year;
    }
    public int getExercises(int week) {
        return this.exercises[week];
    }
    public int getExerciseCount() {
        int palautettava=0;
        for(int i:exercises) {
            palautettava+=i;
        }
        return palautettava;
    }
}
