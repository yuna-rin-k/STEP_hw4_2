import java.io.*;
import java.util.*;

public class LoadPageInfo{

	public static Site loadSite(String pageFilepath, String linksFilepath) {

		ArrayList<Page> pages = readPages(pageFilepath);
		Site site = new Site(pages);

		Map<Page, ArrayList<Page>> links = readLinks(site, linksFilepath);
		site.setLinks(links);

		return site;
	}

	private static ArrayList<Page> readPages(String pagesText) {

		System.out.println("start readPages");
		ArrayList<Page> pages = new ArrayList<>();
		
		try {
			Scanner pagesData = new Scanner(new BufferedInputStream(new FileInputStream(new File(pagesText)),8*1024*1024));
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

	private static Map<Page, ArrayList<Page>> readLinks(Site site, String linksFilepath) {
		
		System.out.println("start readLinks");

		Map<Page, ArrayList<Page>> links = new HashMap<>();

		try {
			Scanner linksData = new Scanner(new BufferedInputStream(new FileInputStream(new File(linksFilepath)),8*1024*1024));

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
}