package com.tomer.quiz.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;

import com.tomer.quiz.GameActivity;
import com.tomer.quiz.PlayVideo;
import com.tomer.quiz.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
    private List<Country> countries;
    private Random random;
    private int life;
    private int index;
    private Map<String, Integer> esterEggs;

    public Game() {
        this.countries = new ArrayList<>();
        this.random = new Random();
        this.esterEggs = new HashMap<>();
        this.life = 3;
        this.index = 0;
        this.init();

    }

    public String getName() {
        return this.countries.get(this.index).getName();
    }

    private void setEsterEggs() {
        this.esterEggs.put("spider-man", R.raw.spiderman);
        this.esterEggs.put("hulk", R.raw.hulk);
        this.esterEggs.put("groot", R.raw.groot);
        this.esterEggs.put("deadpool", R.raw.deadpool);
    }

    public void setIndex(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        for (int i = 0; i < this.countries.size(); i++) {
            Country c = this.countries.get(i);
            if (c.getName().equals(name)) {
                this.index = i;
                return;
            }
        }
    }

    private void init() {
        this.countries.add(new Country("Israel", R.drawable.israel_f));
        this.countries.add(new Country("Brazil", R.drawable.brazil_f));
        this.countries.add(new Country("Australia", R.drawable.australia_f));
        this.countries.add(new Country("Bulgaria", R.drawable.bulgaria));
        this.countries.add(new Country("Canada", R.drawable.canada_f));
        this.countries.add(new Country("China", R.drawable.china_f));
        this.countries.add(new Country("Egypt", R.drawable.egypt_f));
        this.countries.add(new Country("Iraq", R.drawable.iraq_f));
        this.countries.add(new Country("Iran", R.drawable.iran_f));
        this.countries.add(new Country("France", R.drawable.france_f));
        this.countries.add(new Country("Germany", R.drawable.germany_f));
        this.countries.add(new Country("Greece", R.drawable.greece_f));
        this.countries.add(new Country("Italy", R.drawable.italy_f));
        this.countries.add(new Country("Japan", R.drawable.japan_f));
        this.countries.add(new Country("Russia", R.drawable.russia_f));
        this.countries.add(new Country("Switzerland", R.drawable.switzerland_f));
        this.countries.add(new Country("Turkey", R.drawable.turkey_f));
        this.countries.add(new Country("UK", R.drawable.uk_f));
        this.countries.add(new Country("USA", R.drawable.usa_f));
        this.index = this.random.nextInt(this.countries.size());
        this.setEsterEggs();
    }

    public int getCountryFlag() {
        return countries.get(this.index).getFlagId();
    }

    public int getLife() {
        return life;
    }

    public int getEsterEgg(String answer) {

        if (this.esterEggs.containsKey(answer)) {
            return this.esterEggs.get(answer);
        }
        return -1;
    }

    public boolean gussCountry(String answer) {
        boolean success = answer.equals(this.countries.get(this.index).getName());
        if (!success) {
            this.life--;
        }
        return success;
    }

    public void getNextCountry() {
        this.countries.remove(this.index);
        this.index = this.random.nextInt(this.countries.size());

    }
}
