package com.laptrinhjavaweb.api.output;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.NewDTO;

/**
 * @author quang
 *
 */
public class NewOutput {
	private int totalPage;
	private int page;
	private List<NewDTO> listResult = new ArrayList<NewDTO>();

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<NewDTO> getListResult() {
		return listResult;
	}

	public void setListResult(List<NewDTO> listResult) {
		this.listResult = listResult;
	}

}
