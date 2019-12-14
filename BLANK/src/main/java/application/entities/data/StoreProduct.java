package application.entities.data;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StoreProduct {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufactorer")
    private Manufactorer manufactorer;

    @Column(name = "price")
    private Float price;

    @Column(name = "created")
    private DateTime created;

    @Column(name = "updated")
    private DateTime updated;


}
