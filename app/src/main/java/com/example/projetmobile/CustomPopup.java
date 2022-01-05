package com.example.projetmobile;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;

public class CustomPopup extends Dialog {

    private String title;
    private String subTitle;
    private Button button1,button2,button3,button4,button5;

    private TextView titleView, subTitleView;


    public CustomPopup(Activity activity){
        super(activity, R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popup_eval);
        this.title = "Evalution de la Visite";
        this.subTitle = "Veuillez évaluer votre visiite";
        this.button1 = findViewById(R.id.button1);
        this.button2 = findViewById(R.id.button2);
        this.button3 = findViewById(R.id.button3);
        this.button4 = findViewById(R.id.button4);
        this.button5 = findViewById(R.id.button5);
        this.titleView = findViewById(R.id.titrepopup);
        this.subTitleView = findViewById(R.id.textpopup);
    }


    public void setTitle(String title){this.title = title;}
    public void setSubTitle(String subTitle){this.subTitle = subTitle;}

    public Button getButton1(){return button1;}
    public Button getButton2(){return button2;}
    public Button getButton3(){return button3;}
    public Button getButton4(){return button4;}
    public Button getButton5(){return button5;}

    public void build(){
        show();
        titleView.setText(title);
        subTitleView.setText(subTitle);
    }


}
