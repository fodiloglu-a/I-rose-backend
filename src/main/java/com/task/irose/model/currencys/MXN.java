package com.task.irose.model.currencys;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(name = "MXN")
public class MXN extends ItemModel {
}
