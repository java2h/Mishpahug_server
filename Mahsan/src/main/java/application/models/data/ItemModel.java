package application.models.data;

import application.entities.data.Item;
import application.repositories.IshurRepository;
import application.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemModel implements IItemModel{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    IshurRepository ishurRepository;

    @Override
    public List<Item> getBySerials(List<String> serial) {
        return null;//TODO
    }
}
