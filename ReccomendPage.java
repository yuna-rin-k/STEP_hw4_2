//未完成!!
import java.util.*;
public class ReccomendPage {

	public static void main(String[] args) {

		Site site = LoadPageInfo.loadSite("pages.txt", "links.txt");

		ArrayList<Page> listPages = getListPages(site);
		Page target = site.getPageByPageName("津田塾大学");
		Page listPage = getTargetListPage(site, listPages, target);
		
		System.out.println(listPage.pageName);
	}


	static ArrayList<Page> getListPages(Site site) {

		ArrayList<Page> pages = site.getPages();
		ArrayList<Page> listPages = new ArrayList<>();

		for (int i = 0; i < pages.size(); i++) {
			Page page = pages.get(i);
			String pageName = page.pageName;

			if (page.pageName.length() < 3) 
				continue;
				
			if ((pageName.substring(pageName.length()-2, pageName.length())).equals("一覧")) 
				listPages.add(page);
		}
		return listPages;
	}

	static Page getTargetListPage(Site site, ArrayList<Page> listPages, Page target) {
		
		int shotestPath = Integer.MAX_VALUE;
		Page shotestPathPage = null;

		for (int i = 0; i < listPages.size(); i++) {

			Page src = listPages.get(i);
			int pathCount = findShortestPath(src, target, site, shotestPath);

			if (pathCount < shotestPath) {
				shotestPath = pathCount;
				shotestPathPage = src;
			}
		}
		return shotestPathPage;	

	}

	static int findShortestPath(Page src, Page dest, Site site, int shotestPathCount) {

		Map<Page, ArrayList<Page>> links = site.getLinks();

		int[] pathCount = new int[site.getSiteSize()];
		Arrays.fill(pathCount, -1);
		pathCount[src.id] = 0;

		Page prePage = null;

		Queue<Page> q = new ArrayDeque<>();
		q.add(src);
		while (q.size() > 0) {

			Page page = q.poll();
			prePage = page;

			if (page.equals(dest))
				return pathCount[dest.id];
				
			if (!links.containsKey(page)) 
				break;

			if (pathCount[page.id] > shotestPathCount)
				break;

			ArrayList<Page> linkPages = links.get(page);
			for (int i = 0; i < linkPages.size(); i++) {
				Page linkPage = linkPages.get(i);
				if (pathCount[linkPage.id] == -1) {
					pathCount[linkPage.id] = pathCount[linkPage.id]+1;
					q.add(linkPage);
				}	
			} 
		}
		return Integer.MAX_VALUE;
	}
}