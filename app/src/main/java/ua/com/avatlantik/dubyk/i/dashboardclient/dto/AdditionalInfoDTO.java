package ua.com.avatlantik.dubyk.i.dashboardclient.dto;

/**
 * Created by i.dubyk on 07.07.2016.
 */
public class AdditionalInfoDTO {

    private String typeData;
    private int data1;
    private int data2;
    private int data3;
    private int data4;

    public AdditionalInfoDTO(String typeData, int data1, int data2, int data3, int data4) {
        this.typeData = typeData;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }

    public String getTypeData() {
        return typeData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    public int getData1() {
        return data1;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public int getData2() {
        return data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public int getData3() {
        return data3;
    }

    public void setData3(int data3) {
        this.data3 = data3;
    }

    public int getData4() {
        return data4;
    }

    public void setData4(int data4) {
        this.data4 = data4;
    }
}
