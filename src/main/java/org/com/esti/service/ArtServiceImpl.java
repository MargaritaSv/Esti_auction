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

    @Override
    public ArtServiceModel findProductById(Long id) {
        return this.artRepository.findById(id)
                .map(a -> this.modelMapper.map(a, ArtServiceModel.class))
                .orElseThrow(() -> new IllegalArgumentException("Canvas isn't exist."));
    }

    @Override
    public ArtServiceModel editProduct(Long id, ArtServiceModel artServiceModel) {
        Art art = this.artRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Canvas isn't exist."));

        art.setAuthor(artServiceModel.getAuthor());
        art.setDescription(artServiceModel.getDescription());
        art.setHeight(artServiceModel.getHeight());
        art.setWidth(artServiceModel.getWidth());
        art.setPainted(artServiceModel.getPainted());
        art.setEstimateFrom(artServiceModel.getEstimateFrom());
        art.setEstimateFrom(artServiceModel.getEstimateTo());
        art.setImageUrl(artServiceModel.getImageUrl());

        //TODO:estimatedBy
        return this.modelMapper.map(this.artRepository.save(art), ArtServiceModel.class);
    }
}
