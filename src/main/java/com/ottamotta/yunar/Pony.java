package com.ottamotta.yunar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cabs")
public class Pony {

    @Id
    String name;

    @Column
    int total;

    @Column
    int rented;
}
