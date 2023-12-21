package fr.skytryx.arkmmo;

import java.util.HashMap;

public class Database {

    public HashMap<String, Object> data;
    public String name;

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
}
