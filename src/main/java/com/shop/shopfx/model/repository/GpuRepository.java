package com.shop.shopfx.model.repository;

import com.shop.shopfx.model.entity.GraphicsCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuRepository extends JpaRepository<GraphicsCard, Long> {
}
