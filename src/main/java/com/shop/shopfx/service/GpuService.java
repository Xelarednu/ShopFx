package com.shop.shopfx.service;

import com.shop.shopfx.model.entity.GraphicsCard;
import com.shop.shopfx.model.repository.GpuRepository;
import com.shop.shopfx.security.Role;
import com.shop.shopfx.security.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GpuService implements com.shop.shopfx.interfaces.GpuService  {
    private GpuRepository gpuRepository;
    private SessionManager sessionManager;

    public GpuService(SessionManager sessionManager, GpuRepository gpuRepository) {
        this.sessionManager = sessionManager;
        this.gpuRepository = gpuRepository;
    }

    public Optional<GraphicsCard> add(GraphicsCard graphicsCard) {
        try {
            if(sessionManager.isLoggedIn() && sessionManager.hasRole(Role.MANAGER)){
                GraphicsCard addedGpu = gpuRepository.save(graphicsCard);
                return Optional.of(addedGpu);
            }else{
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<GraphicsCard> getObservableList(){
        ObservableList<GraphicsCard> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }

    @Override
    public List<GraphicsCard> getList() {
        if (sessionManager.isLoggedIn() && sessionManager.hasRole(Role.USER)) {
            return gpuRepository.findAll();
        } else {
            return new ArrayList<>();
        }
    }
}