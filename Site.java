import java.util.*;

public class Site {

	private ArrayList<Page> pages;
	private Map<Page, ArrayList<Page>> links;
	private Map<Integer, Page> idKey_pageVal;
	private Map<String, Page> pageNameKey_pageVal;

	public Site(ArrayList<Page> pages) {
		this.pages = pages;
		this.links = links;
		creatMapWithID();
		creatMapWithPageName();
	}

	public void setLinks(Map<Page, ArrayList<Page>> links) {
		this.links = links;
	}

	private void creatMapWithID() {

		this.idKey_pageVal = new HashMap<>();
		for(int i = 0;i < pages.size(); i++) {
			Page page = pages.get(i);
			int id = page.id;
			idKey_pageVal.put(id, page);
		}
	}

	private void creatMapWithPageName() {

		this.pageNameKey_pageVal = new HashMap<>();
		for (int i = 0; i < pages.size(); i++) {
			Page page = pages.get(i);
			String pageName = page.pageName;
			pageNameKey_pageVal.put(pageName, page);
		}
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}

	public Map<Page, ArrayList<Page>> getLinks() {
		return this.links;
	}

	public Page getPageById(int id) {
		return idKey_pageVal.get(id);
	}

	public Page getPageByPageName(String pageName) {
		return pageNameKey_pageVal.get(pageName);
	}
}
