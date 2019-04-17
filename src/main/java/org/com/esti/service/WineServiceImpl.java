package org.com.esti.service;

import org.com.esti.domain.entities.Wine;
import org.com.esti.models.service.WineServiceModel;
import org.com.esti.repository.WineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WineServiceImpl implements WineService {
    private final WineRepository wineRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WineServiceImpl(WineRepository wineRepository, ModelMapper modelMapper) {
        this.wineRepository = wineRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public WineServiceModel add(WineServiceModel wineServiceModel) {
        Wine wine = this.modelMapper.map(wineServiceModel, Wine.class);
        try {
            wine = this.wineRepository.saveAndFlush(wine);
            return this.modelMapper.map(wine, WineServiceModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public List<WineServiceModel> findAll() {
        return this.wineRepository.findAll()
                .stream()
                .map(w -> this.modelMapper.map(w, WineServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public WineServiceModel findProductById(Long id) {
        return this.wineRepository.findById(id)
                .map(w -> this.modelMapper.map(w, WineServiceModel.class))
                .orElseThrow(() -> new IllegalArgumentException("Wine isn't found."));
    }

    @Override
    public WineServiceModel editWine(Long id, WineServiceModel wineServiceModel) {
        Wine wine = this.wineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wine isn't found."));

        wine.setCollection(wineServiceModel.getCollection());
        wine.setNumberOfBottles(wineServiceModel.getNumberOfBottles());
        wine.setEstimateFrom(wineServiceModel.getEstimateFrom());
        wine.setEstimateTo(wineServiceModel.getEstimateTo());
        wine.setCreatedAt(wineServiceModel.getCreatedAt());
        wine.setImageUrl(wineServiceModel.getImageUrl());

        return this.modelMapper.map(this.wineRepository.save(wine),WineServiceModel.class);
    }

    @Override
    public void deleteWine(Long id) throws Exception {
        try {
            this.wineRepository.deleteById(id);
        }catch (Exception ex){
            throw new Exception("Wine isn't found.");
        }
    }
}
