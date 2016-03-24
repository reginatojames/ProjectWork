package com.example.reginatojames.projectwork;

/**
 * Created by Reginato James on 24/03/2016.
 */
public class Indirizzo {
    private String type;
    private String name;
    private String number;
    private String intern;

    public Indirizzo(String t, String nam, String num, String in) {
        type = t;
        name = nam;
        number = num;
        if(in.length() > 0)
            intern = "interno " + in;
        else
            intern = "";
    }
    public Indirizzo(){
        type = null;
        name = null;
        number = null;
        intern = null;
    }

    public String toString(){
        return type+" "+name+" "+number+" "+intern;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getIntern() {
        return intern;
    }
    public void setIntern(String intern) {
        this.intern = intern;
    }

    public String getAddress(){
        return (type+name+number).replace(" ", "");
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Indirizzo){
            Indirizzo temp = (Indirizzo)other;
            if(temp.getAddress().equals(this.getAddress())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return (type+name+number).replace(" ", "").hashCode();
    }
}
