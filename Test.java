import java.util.*;
import java.io.*;

public class Test {
	public static void main(String[] args) {

		ArrayList<Page> pages = readPages();
		Site site = new Site(pages);
		site.creatMapWithID();
		readLinks(site);
	}		

	static ArrayList<Page> readPages() {

		ArrayList<Page> pages = new ArrayList<>();
		
		try {
			Scanner pagesData = new Scanner(new File("pages.txt"));
			while (pagesData.hasNext()) {

				int id = pagesData.nextInt();
				String pageName = pagesData.next();

				pages.add(new Page(id, pageName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("finish readPages");
		return pages;	 
	}

	static Map<Page, ArrayList<Page>> readLinks(Site site) {

		Map<Page, ArrayList<Page>> links = new HashMap<>();

		try {
			Scanner linksData = new Scanner(new File("links.txt"));
			while (linksData.hasNext()) {

				int srcID = linksData.nextInt();
				int dstID = linksData.nextInt();

				Page src = site.getPageById(srcID);
				Page dst = site.getPageById(dstID);

				ArrayList<Page> dstArray;

				if (links.containsKey(src)) dstArray = links.get(src);
				else dstArray = new ArrayList<>();

				dstArray.add(dst);
				links.put(src, dstArray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("finish readLinks");
		return links;
	}

	static class Page {

		int id;
		String pageName;

		public Page(int id, String pageName) {
			this.id = id;
			this.pageName = pageName;
		}
	}

	static class Site {

		ArrayList<Page> pages;
		Map<Integer, Page> idKey_pageVal;

		public Site(ArrayList<Page> pages) {
			this.pages = pages;
		}
		private void creatMapWithID() {

			idKey_pageVal = new HashMap<>();
			for(int i = 0; i < pages.size(); i++) {
				Page page = pages.get(i);
				int id = page.id;
				idKey_pageVal.put(id, page);
			}
		}

		public Page getPageById(int id) {
			return idKey_pageVal.get(id);
		}
	}
}