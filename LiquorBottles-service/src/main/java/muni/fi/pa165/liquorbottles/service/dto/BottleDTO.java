package muni.fi.pa165.liquorbottles.serviceLayer.dto;

import java.util.Date;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleDTO {

    private long id;
    private StoreDTO store;
    private BottleTypeDTO bottleType;
    private long batchNumber;
    private long stamp;
    private Date dateOfBirth;
    private ToxicityDTO toxicity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StoreDTO getStore() {
        return store;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }

    public long getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ToxicityDTO getToxicity() {
        return toxicity;
    }

    public void setToxicity(ToxicityDTO toxicity) {
        this.toxicity = toxicity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BottleDTO other = (BottleDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BottleDTO{" + "id=" + id + ", store=" + store + ", bottleType="
                + bottleType + ", batchNumber=" + batchNumber + ", stamp="
                + stamp + ", dateOfBirth=" + dateOfBirth + ", toxicity=" + toxicity + '}';
    }

}
