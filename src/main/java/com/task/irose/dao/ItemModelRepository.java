package com.task.irose.dao;

import com.task.irose.model.currencys.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemModelRepository extends JpaRepository<ItemModel,Long> {
}
