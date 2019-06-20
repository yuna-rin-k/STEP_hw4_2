import java.io.*;
import java.util.*;

public class LoadPageInf{

	private ArrayList<Page> pages;
	private Map<Page, ArrayList<Page>> links;
	String pagesText;
	String linkText;
	private boolean linkIsLorded;

	public LoadPageInf(String pagesText, String linkText) {

		this.pagesText = pagesText;
		this.pages = readPages(pagesText);
		this.linkText = linkText;
		linkIsLorded = false;
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}

	public Map<Page, ArrayList<Page>> getLinks(Site site) {
		if (!linkIsLorded) {
			this.links = readLinks(site);
		}
		return links;
	}

	private ArrayList<Page> readPages(String pagesText) {

		ArrayList<Page> pages = new ArrayList<>();
		
		try {
			Scanner pagesData = new Scanner(new BufferedInputStream(
												new FileInputStream(new File(pagesText))));
			while (pagesData.hasNext()) {

				int id = pagesData.nextInt();
				String pageName = pagesData.next();

				pages.add(new Page(id, pageName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pages;	 
	}

	private Map<Page, ArrayList<Page>> readLinks(Site site) {

		Map<Page, ArrayList<Page>> links = new HashMap<>();

		try {
			Scanner linksData = new Scanner(new BufferedInputStream(
												new FileInputStream(new File(linkText))));

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
		return links;
	}
}