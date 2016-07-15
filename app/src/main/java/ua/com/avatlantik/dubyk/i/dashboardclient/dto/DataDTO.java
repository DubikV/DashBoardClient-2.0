package ua.com.avatlantik.dubyk.i.dashboardclient.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by i.dubyk on 11.07.2016.
 */
public class DataDTO {
    private Date dateDownload;
    private ArrayList<SalesUGKDTO> salesUGKDTO;
    private ArrayList<SalesUGKTableDTO> salesUGKTableDTO;

    private ArrayList<MoneyDTO> moneyDTO;
    private ArrayList<MoneyTableDTO> moneyTableDTO;

    private ArrayList<AdditionalInfoDTO> additionalInfoDTO;

    private static volatile DataDTO _instance = null;

    private DataDTO() {}

    public static DataDTO getInstance() {

        if (_instance == null)
            synchronized (DataDTO.class) {
                if (_instance == null)
                    _instance = new DataDTO();
            }

        return _instance;

    }

    public Date getDateDownload() {
        return dateDownload;
    }

    public void setDateDownload(Date dateDownload) {
        this.dateDownload = dateDownload;
    }

    public ArrayList<SalesUGKDTO> getSalesUGKDTO() {
        return salesUGKDTO;
    }

    public void setSalesUGKDTO(ArrayList<SalesUGKDTO> salesUGKDTO) {
        this.salesUGKDTO = salesUGKDTO;
    }

    public ArrayList<SalesUGKTableDTO> getSalesUGKTableDTO() {
        return salesUGKTableDTO;
    }

    public void setSalesUGKTableDTO(ArrayList<SalesUGKTableDTO> salesUGKTableDTO) {
        this.salesUGKTableDTO = salesUGKTableDTO;
    }

    public ArrayList<AdditionalInfoDTO> getAdditionalInfoDTO() {
        return additionalInfoDTO;
    }

    public void setAdditionalInfoDTO(ArrayList<AdditionalInfoDTO> additionalInfoDTO) {
        this.additionalInfoDTO = additionalInfoDTO;
    }

    public ArrayList<MoneyTableDTO> getMoneyTableDTO() {
        return moneyTableDTO;
    }

    public void setMoneyTableDTO(ArrayList<MoneyTableDTO> moneyTableDTO) {
        this.moneyTableDTO = moneyTableDTO;
    }

    public ArrayList<MoneyDTO> getMoneyDTO() {
        return moneyDTO;
    }

    public void setMoneyDTO(ArrayList<MoneyDTO> moneyDTO) {
        this.moneyDTO = moneyDTO;
    }
}
