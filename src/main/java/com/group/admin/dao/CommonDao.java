package com.group.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.admin.vo.BaseVO;

@Repository
public interface CommonDao<T extends BaseVO> extends JpaRepository<T, Long>{

	
}
