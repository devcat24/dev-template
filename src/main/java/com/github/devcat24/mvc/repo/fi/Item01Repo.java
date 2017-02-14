package com.github.devcat24.mvc.repo.fi;

import com.github.devcat24.mvc.entity.fi.Item01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("Item01Repo")
public interface Item01Repo extends JpaRepository<Item01, Integer> {
    Item01 findByItemno(Integer itemno);
}
