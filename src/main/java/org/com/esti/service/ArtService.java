package org.com.esti.service;

import org.com.esti.models.service.ArtServiceModel;

import java.util.List;

public interface ArtService {
    ArtServiceModel add(ArtServiceModel artServiceModel);

    List<ArtServiceModel> findAll();
}
