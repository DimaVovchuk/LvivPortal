package com.lab.epam.dao;

/**
 * Created by Admin on 10.06.2015.
 */

import java.io.Serializable;


public interface Identified<PK extends Serializable> {
    public PK getId();
}
