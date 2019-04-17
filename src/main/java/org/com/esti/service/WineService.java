package org.com.esti.service;

import org.com.esti.models.service.WineServiceModel;

import java.util.List;

public interface WineService {
    WineServiceModel add(WineServiceModel artServiceModel);

    List<WineServiceModel> findAll();

    WineServiceModel findProductById(Long id);

    WineServiceModel editWine(Long id, WineServiceModel artServiceModel);

    void deleteWine(Long id) throws Exception;
}
