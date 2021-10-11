package subpage.model.vo;

public class Search {
	private String searchBox;
	
	public Search() {}

	public Search(String searchBox) {
		super();
		this.searchBox = searchBox;
	}

	public String getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(String searchBox) {
		this.searchBox = searchBox;
	}

	@Override
	public String toString() {
		return "Search [searchBox=" + searchBox + "]";
	}
	

}
