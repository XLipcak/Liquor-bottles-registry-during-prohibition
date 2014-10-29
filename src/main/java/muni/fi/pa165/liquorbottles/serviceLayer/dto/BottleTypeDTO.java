package muni.fi.pa165.liquorbottles.serviceLayer.dto;

import muni.fi.pa165.liquorbottles.persistenceLayer.entities.Producer;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleTypeDTO {

    private long id;
    private String name;
    private String alcType;
    private int power;
    private int volume;
    private ProducerDTO producer;

    public long getId() {
        return id;
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
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final BottleTypeDTO other = (BottleTypeDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BottleTypeDTO{" + "id=" + id + ", name=" + name + ", alcType=" +
                alcType + ", power=" + power + ", volume=" + volume + '}';
    }
    
    
}
