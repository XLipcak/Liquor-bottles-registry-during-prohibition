package muni.fi.pa165.liquorbottles.persistenceLayer.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @JoinColumn(nullable = false, referencedColumnName = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BottleType bottleType;

    @Column(nullable = false)
    private long batchNumber;

    @Column(nullable = false, unique = true)
    private long stamp;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    private Toxicity toxicity;

    public Bottle() {

    }

    public Bottle(Store store, BottleType bottleType, long batchNumber, long stamp, Date dateOfBirth, Toxicity toxicity) {
        this.store = store;
        this.bottleType = bottleType;
        this.batchNumber = batchNumber;
        this.stamp = stamp;
        this.dateOfBirth = dateOfBirth;
        this.toxicity = toxicity;
    }

    

    public long getBatchNumber() {
        return batchNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Toxicity getToxicity() {
        return toxicity;
    }

    public void setBottleType(BottleType bottleType) {
        this.bottleType = bottleType;
    }

    public void setBatchNumber(long batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setToxicity(Toxicity toxicity) {
        this.toxicity = toxicity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
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
