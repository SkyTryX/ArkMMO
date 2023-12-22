package fr.skytryx.arkmmo;

import java.util.HashMap;

public class Database {

    HashMap<String, Object> data = new HashMap<>();
    String name;

    public Database(String n){
        name = n;
    }

    public boolean load(){
        return false;
    }

    public boolean save(){
        return false;
    }

    public String getName(){
        return name;
    }

    public HashMap<String, Object> getDatas(){
        return data;
    }

    public void addData(String s, Object d){
        data.put(s, d);
    }

    public void removeData(String s){
        data.remove(s);
    }

    public Object getData(String s){
        return data.get(s);
    }

    public boolean containsData(String s){
        return data.containsKey(s);
    }
}
