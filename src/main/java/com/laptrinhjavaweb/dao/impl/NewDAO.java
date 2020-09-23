package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO{

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		//String sql  = "Select * from news LIMIT ?, ?";
		StringBuilder sql = new StringBuilder("Select * from news");
		
		if(pageble.getOffset() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			
		}
		return query(sql.toString(), new NewMapper());
		
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "select * from news where id = ?";
		List<NewModel> news =  query(sql, new NewMapper(),id);
		return news.isEmpty() ? null : news.get(0);
		}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
			String sql = "select * from news where categoryid = ?";
			return query(sql, new NewMapper(),categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		String sql = "insert into news (title,thumbnail, shortdescription, content, categoryid, createdate,createby) values (?,?,?,?,?,?,?)";
		return insert(sql, newModel.getTitle(),newModel.getThumbnail(),newModel.getShortDescription(),newModel.getContent(),
				newModel.getCategoryId(),newModel.getCreatedDate(),
					newModel.getCreatedBy());
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title=?,thumbnail = ?,");
		sql.append("shortdescription = ?, content = ?, categoryid = ?,");
		sql.append("modifieddate = ?,modifiedby = ? WHERE id = ?");
		update(sql.toString(),updateNew.getTitle(),updateNew.getThumbnail(),updateNew.getShortDescription(),updateNew.getContent(),
		updateNew.getCategoryId(), updateNew.getModifiedDate(), updateNew.getModifiedBy(),updateNew.getId());
		
	}

	@Override
	public void delete(long id) {
		String sql ="DELETE FROM news WHERE id = ?";
		update(sql,id);
		
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}

}
