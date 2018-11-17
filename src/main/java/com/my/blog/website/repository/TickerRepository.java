package com.my.blog.website.repository;


import com.my.blog.website.entity.TickerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tickerRepository")
public interface TickerRepository extends JpaRepository<TickerTable, String> {

}
