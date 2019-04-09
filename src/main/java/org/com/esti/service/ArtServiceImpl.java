package org.com.esti.service;

import org.com.esti.domain.entities.Art;
import org.com.esti.models.service.ArtServiceModel;
import org.com.esti.repository.ArtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtServiceImpl implements ArtService {
    private final ArtRepository artRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtServiceImpl(ArtRepository artRepository, ModelMapper modelMapper) {
        this.artRepository = artRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArtServiceModel add(ArtServiceModel artServiceModel) {
        Art art = this.modelMapper.map(artServiceModel, Art.class);

        try {
            art = this.artRepository.saveAndFlush(art);
            return this.modelMapper.map(art, ArtServiceModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
