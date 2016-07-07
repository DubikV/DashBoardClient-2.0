package ua.com.avatlantik.dubyk.i.dashboardclient.dto;

/**
 * Created by i.dubyk on 07.07.2016.
 */
public class SalesUGKDTO {

    private String typeData;
    private int numberDay;
    private int valye;

    public SalesUGKDTO(String typeData, int numberDay, int valye) {
        this.typeData = typeData;
        this.numberDay = numberDay;
        this.valye = valye;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public int getValye() {
        return valye;
    }

    public void setValye(int valye) {
        this.valye = valye;
    }
}
