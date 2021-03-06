package application.models.logsdata;

import application.entities.log.LogsDataEntity;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ILogsDataModel {
    public LogsDataEntity add(LogsDataEntity data);

    public void clear();

    public List<LogsDataEntity> getAll();

    public Iterable<LogsDataEntity> getAll(Predicate predicate);

    public void delete(Predicate predicate);

}
