package dao;

import java.util.List;

import model.Statistics;

public interface IStatisticsRepository extends IRepository<Statistics>{
	public List<Statistics> byId(Integer id);
}
