package application.models.data;

import application.entities.data.Item;

import java.util.List;

public interface IItemModel {
    public List<Item> getBySerials(List<String> serial);

}
