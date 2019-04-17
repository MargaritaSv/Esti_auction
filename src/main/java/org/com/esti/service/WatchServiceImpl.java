package org.com.esti.service;

import org.com.esti.domain.entities.Watch;
import org.com.esti.models.service.WatchServiceModel;
import org.com.esti.repository.WatchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WatchServiceImpl(WatchRepository watchRepository, ModelMapper modelMapper) {
        this.watchRepository = watchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WatchServiceModel add(WatchServiceModel watchServiceModel) {
        Watch watch = this.modelMapper.map(watchServiceModel, Watch.class);
        try {
            watch = this.watchRepository.saveAndFlush(watch);
            return this.modelMapper.map(watch, WatchServiceModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WatchServiceModel> findAll() {
        return this.watchRepository.findAll()
                .stream()
                .map(w -> this.modelMapper.map(w, WatchServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public WatchServiceModel findProductById(Long id) {

        return this.watchRepository.findById(id)
                .map(w -> this.modelMapper.map(w, WatchServiceModel.class))
                .orElseThrow(() -> new IllegalArgumentException("Watch isn't found."));
    }

    @Override
    public WatchServiceModel editProduct(Long id, WatchServiceModel watchServiceModel) {
        Watch watch = this.watchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Watch isn't found."));

        watch.setName(watchServiceModel.getName());
        watch.setCollection(watchServiceModel.getCollection());
        watch.setDial(watchServiceModel.getDial());
        watch.setClosure(watchServiceModel.getClosure());
        watch.setDimensions(watchServiceModel.getDimensions());
        watch.setEstimateFrom(watchServiceModel.getEstimateFrom());
        watch.setEstimateTo(watchServiceModel.getEstimateTo());

        return this.modelMapper.map(this.watchRepository.save(watch), WatchServiceModel.class);
    }

    @Override
    public void deleteWatch(Long id) throws Exception {
        try {
            this.watchRepository.deleteById(id);
        } catch (Exception ex) {
            throw new Exception("Watch isn't found.");
        }
    }
}
