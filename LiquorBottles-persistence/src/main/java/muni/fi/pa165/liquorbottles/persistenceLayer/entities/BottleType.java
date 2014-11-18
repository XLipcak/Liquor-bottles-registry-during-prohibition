package muni.fi.pa165.liquorbottles.persistenceLayer.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Michal Taraj, Masaryk University
 */

@Entity
@Table(name = "bottleTypes")
public class BottleType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bottleType_id")
    private long id;
    
    @Column
    private String name;
    
    @Column
    private String alcType;
    
    @Column
    private int power;
    
    @Column
    private int volume;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "producer_id")
    private Producer producer;
    
    public BottleType(){
        
    }

    public BottleType(String name, String alcType, int power, int volume, Producer producer) {
        this.name = name;
        this.alcType = alcType;
        this.power = power;
        this.volume = volume;
        this.producer = producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
    
    public long getId() {
        return id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlcType() {
        return alcType;
    }

    public void setAlcType(String alcType) {
        this.alcType = alcType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final BottleType other = (BottleType) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
