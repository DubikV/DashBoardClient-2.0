package ua.com.avatlantik.dubyk.i.dashboardclient.dto.Money;

/**
 * Created by i.dubyk on 07.07.2016.
 */
public class MoneyTableDTO {

    private String typeData;
    private int sumMonth;
    private int sumDay;
    private int delta12;
    private int delta3;
    private int delta1;

    public MoneyTableDTO(String typeData, int sumMonth, int sumDay, int delta12, int delta3, int delta1) {
        this.typeData = typeData;
        this.sumMonth = sumMonth;
        this.sumDay = sumDay;
        this.delta12 = delta12;
        this.delta3 = delta3;
        this.delta1 = delta1;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    public int getSumMonth() {
        return sumMonth;
    }

    public void setSumMonth(int sumMonth) {
        this.sumMonth = sumMonth;
    }

    public int getDelta12() {
        return delta12;
    }

    public void setDelta12(int delta12) {
        this.delta12 = delta12;
    }

    public int getSumDay() {
        return sumDay;
    }

    public void setSumDay(int sumDay) {
        this.sumDay = sumDay;
    }

    public int getDelta3() {
        return delta3;
    }

    public void setDelta3(int delta3) {
        this.delta3 = delta3;
    }

    public int getDelta1() {
        return delta1;
    }

    public void setDelta1(int delta1) {
        this.delta1 = delta1;
    }
}
