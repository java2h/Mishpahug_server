package application.utils;

import application.dtoes.data.OptionDTO;
import application.entities.data.OptionEntity;
import application.models.data.device.IDeviceModel;
import application.models.data.sensor.ISensorModel;
import org.springframework.beans.factory.annotation.Autowired;

public class OptionConverter {
    @Autowired
    IDeviceModel deviceModel;

    @Autowired
    ISensorModel sensorModel;
    public OptionEntity converter(OptionDTO data){

        OptionEntity res = new OptionEntity();

        res.setNameOption(data.getName());
        res.setType(data.getType());
        res.setIfType(data.getIfType());
        res.setDateS(data.getDate());
        res.setTimeS(data.getTime());
        res.setSensor(sensorModel.getByName(data.getSensorName()));
        res.setDevice(deviceModel.getByName(data.getDeviceName()));

        return res;
    }
}
