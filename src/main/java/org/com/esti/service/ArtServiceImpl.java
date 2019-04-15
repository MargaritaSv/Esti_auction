package org.com.esti.service;

import org.com.esti.domain.entities.Art;
import org.com.esti.models.service.ArtServiceModel;
import org.com.esti.repository.ArtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtServiceImpl implements ArtService {
    private final ArtRepository artRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinarySErvice;

    @Autowired
    public ArtServiceImpl(ArtRepository artRepository, ModelMapper modelMapper, CloudinaryService cloudinarySErvice) {
        this.artRepository = artRepository;
        this.modelMapper = modelMapper;
        this.cloudinarySErvice = cloudinarySErvice;
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

    @Override
    public List<ArtServiceModel> findAll() {
        return this.artRepository.findAll()
                .stream()
                .map(a -> this.modelMapper.map(a, ArtServiceModel.class))
                .collect(Collectors.toList());
    }
}
