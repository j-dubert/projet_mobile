package com.example.projetmobile;

public class Avisweb {

    private String _websitename;
    private int _score;

    public Avisweb(String str, int i1){
        _websitename = str;
        _score = i1;
    }

    public Avisweb(){

    }

    public String get_web(){
        return _websitename;
    }

    public int get_score(){
        return _score;
    }


    public void set_websitename (String string){this._websitename=string ;}
    public void set_score(int i){this._score = i;}
}
