package org.com.esti.service;

import org.com.esti.models.service.WatchServiceModel;

import java.util.List;

public interface WatchService {
    WatchServiceModel add(WatchServiceModel watherviceModel);

    List<WatchServiceModel> findAll();

    WatchServiceModel findProductById(Long id);

    WatchServiceModel editProduct(Long id, WatchServiceModel watchServiceModel);

    void deleteWatch(Long id) throws Exception;
}
