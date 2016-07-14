package ua.com.avatlantik.dubyk.i.dashboardclient.Modules;

import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingConnect;
import ua.com.avatlantik.dubyk.i.dashboardclient.Settings.SettingsUser;


public class Module_GetURL {
    private SettingsUser settingsUser;
    private SettingConnect settingConnect;

    public Module_GetURL() {
        settingsUser = SettingsUser.getInstance();
        settingConnect = SettingConnect.getInstance();
    }

    public String getGetURL(String dataName){

        return "http://"+settingConnect.getAdressServer()+"/"+dataName+"?login="+getusrlogin()+"&password="+settingsUser.getUserPassword();
    }

    private String getusrlogin(){
        String usrLog = settingsUser.getUserLogin();
        String arrStr[] = usrLog.split(" ");
        String usrlogin ="";
        for (int i =0; i< arrStr.length; i++){
            if(i==arrStr.length-1){
                usrlogin = usrlogin+arrStr[i];
                continue;
            }
            usrlogin = usrlogin+arrStr[i]+"%20";
        }

        return usrlogin;
    }



}
