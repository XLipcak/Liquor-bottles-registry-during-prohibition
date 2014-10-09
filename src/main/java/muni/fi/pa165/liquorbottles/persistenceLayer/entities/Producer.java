package muni.fi.pa165.liquorbottles.persistenceLayer.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
@Entity
@PrimaryKeyJoinColumn(name = "producer_id", referencedColumnName = "user_id")
public class Producer extends User {
    @OneToMany(mappedBy = "procuder")
    private List<BottleType> bottleTypes;

    @Column
    private String name;

    @Column
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
