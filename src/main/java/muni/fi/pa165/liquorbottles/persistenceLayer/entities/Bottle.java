package muni.fi.pa165.liquorbottles.persistenceLayer.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Matus Novak, Masaryk University
 */
@Entity
@Table(name = "bottles")
public class Bottle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bottle_id")
    private long id; 
    
    @ManyToOne
    private Store store;
    
    @ManyToOne
    private BottleType bottleType;

    @Column
    private long batchNumber;

    @Column
    private String dateOfBirth;

    @Column
    private int isToxic;

    public long getBatchNumber() {
        return batchNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getIsToxic() {
        return isToxic;
    }
    
     public void setBottleType(BottleType bottleType) {
        this.bottleType = bottleType;
    }

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIsToxic(int isToxic) {
        this.isToxic = isToxic;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Bottle other = (Bottle) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
