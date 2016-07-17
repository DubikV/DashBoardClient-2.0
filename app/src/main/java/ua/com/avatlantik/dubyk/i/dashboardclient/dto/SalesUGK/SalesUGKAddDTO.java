package ua.com.avatlantik.dubyk.i.dashboardclient.dto.SalesUGK;

/**
 * Created by i.dubyk on 07.07.2016.
 */
public class SalesUGKAddDTO {

    private double planeNormUGK;
    private double plane;
    private double fact;
    private double factNormUGK;

    public SalesUGKAddDTO(double planeNormUGK, double plane, double fact, double factNormUGK) {
        this.planeNormUGK = planeNormUGK;
        this.plane = plane;
        this.fact = fact;
        this.factNormUGK = factNormUGK;
    }

    public double getPlaneNormUGK() {
        return planeNormUGK;
    }

    public void setPlaneNormUGK(double planeNormUGK) {
        this.planeNormUGK = planeNormUGK;
    }

    public double getPlane() {
        return plane;
    }

    public void setPlane(double plane) {
        this.plane = plane;
    }

    public double getFact() {
        return fact;
    }

    public void setFact(double fact) {
        this.fact = fact;
    }

    public double getFactNormUGK() {
        return factNormUGK;
    }

    public void setFactNormUGK(double factNormUGK) {
        this.factNormUGK = factNormUGK;
    }
}
