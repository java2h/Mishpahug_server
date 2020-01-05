package application.models.data;

import application.repositories.IshurRepository;
import application.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemModel {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    IshurRepository ishurRepository;
}
