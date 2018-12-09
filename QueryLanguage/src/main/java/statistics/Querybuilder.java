/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.util.ArrayList;
import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author kape
 */
public class Querybuilder {

    Matcher matcher;

    public Querybuilder() {
        this.matcher = new All();

    }

    public Matcher build() {
        Matcher ret=this.matcher;
        this.matcher=new All();
        return ret;
        
    }

    public Querybuilder playsIn(String attribute) {
        matcher = new And(new PlaysIn(attribute), matcher);
        return this;
    }

    public Querybuilder hasAtLeast(int value, String category) {
        matcher = new And(new HasAtLeast(value, category), matcher);
        return this;
    }

    public Querybuilder hasFewerThan(int value, String category) {
        matcher = new And(new HasFewerThan(value, category), matcher);
        return this;
    }
    public Querybuilder oneOf(Matcher... matchers) {
        matcher=new Or(matchers);
        return this;
    }

}
