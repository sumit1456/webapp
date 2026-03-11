package com.rasthrabhasha.common.dto;

import java.util.List;
import org.springframework.data.domain.Page;

public class PageResponse<T> {

	 private List<T> content;
	    private int number;
	    private int size;
	    private long totalElements;

	    public PageResponse() {}

	    public PageResponse(Page<T> page) {
	        this.content = page.getContent();
	        this.number = page.getNumber();
	        this.size = page.getSize();
	        this.totalElements = page.getTotalElements();
	    }

		public List<T> getContent() {
			return content;
		}

		public void setContent(List<T> content) {
			this.content = content;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public long getTotalElements() {
			return totalElements;
		}

		public void setTotalElements(long totalElements) {
			this.totalElements = totalElements;
		}
}