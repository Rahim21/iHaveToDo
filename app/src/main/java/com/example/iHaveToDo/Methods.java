package com.example.iHaveToDo;

public class Methods {
    static final String SHARED_PREF_FILE_NAME = "com.example.ReminderApp.SHARED_PREF_FILE";
    public void setColorTheme(){

        switch (com.example.iHaveToDo.Constant.color){
            case 0xffF44336:
                com.example.iHaveToDo.Constant.theme = R.style.AppTheme_red;
                break;
            case 0xffE91E63:
                com.example.iHaveToDo.Constant.theme = R.style.AppTheme_pink;
                break;
           /* case 0xff9C27B0:
                com.example.myalarm.Constant.theme = R.style.AppTheme_darpink;
                break;*/
           /* case 0xff673AB7:
                com.example.myalarm.Constant.theme= R.style.AppTheme_violet;
                break;*/
            case 0xff3F51B5:
                com.example.iHaveToDo.Constant.theme = R.style.AppTheme_blue;
                break;
           /* case 0xff03A9F4:
               com.example.myalarm.Constant.theme= R.style.AppTheme_skyblue;
                break;*/
            case 0xff4CAF50:
                com.example.iHaveToDo.Constant.theme = R.style.AppTheme_green;
                break;
           /* case 0xff9E9E9E:
                com.example.myalarm.Constant.theme = R.style.AppTheme_grey;
                break;
            case 0xff795548:
                com.example.myalarm.Constant.theme = R.style.AppTheme_brown;
                break;*/
            default:
                com.example.iHaveToDo.Constant.theme = R.style.AppTheme;
                break;
        }
    }
}
