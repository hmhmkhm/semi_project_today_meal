package common.paging.model.vo;

public class PageInfo {
	private int page;
	private int itemCount;
	private int pageLimit;
	private int itemLimit;
	private int maxPage;
	private int startPage;
	private int endPage;
	
	public PageInfo() {}

	public PageInfo(int page, int itemCount, int pageLimit, int itemLimit) {
		super();
		this.page = page;
		this.itemCount = itemCount;
		this.pageLimit = pageLimit;
		this.itemLimit = itemLimit;
		
		this.maxPage = (int)Math.ceil((double)itemCount / itemLimit);
		this.startPage = (page - 1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(int itemLimit) {
		this.itemLimit = itemLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", itemCount=" + itemCount + ", pageLimit=" + pageLimit + ", itemLimit="
				+ itemLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	
}
