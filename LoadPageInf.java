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
		this.pages = readPages();
		this.linkText = linkText;
		linkIsLorded = false;
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}

	public Map<Page, ArrayList<Page>> getLinks(Site site) {
		if (!linkIsLorded) {
			System.out.println("getLinks");
			readLinks(site);
		}
		return links;
	}

	private ArrayList<Page> readPages() {
		System.out.println("readPages");

		File file = null;
		BufferedReader pagesData = null;

		ArrayList<Page> pages = new ArrayList<>();

		try {
			pagesData = new BufferedReader(new FileReader(pagesText));
			String page = pagesData.readLine();

			int count = 0;

			while (page != null ) {
				StringTokenizer tokenizer = new StringTokenizer(page);
				int id = Integer.parseInt(tokenizer.nextToken());
				String pageName = tokenizer.nextToken();
				pages.add(new Page(id, pageName));
				page = pagesData.readLine();
				count++;
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (pagesData != null) {
				try {
					pagesData.close();
				} catch(IOException e) {}
			}
		}
		return pages;
	}

	private Map<Page, ArrayList<Page>> readLinks(Site site) {
		System.out.println("readLinks");
		linkIsLorded = true;

		File file = null;
		BufferedReader linkData = null;

		Map<Page, ArrayList<Page>> links = new HashMap<>();

		try {
			linkData = new BufferedReader(new FileReader(linkText));
			String link = linkData.readLine();

			int count = 0;

			while (link != null) {
				//System.out.println("count"+count);
				//System.out.println("link "+link);

				StringTokenizer tokenizer = new StringTokenizer(link);
				int srcID = Integer.parseInt(tokenizer.nextToken());
				int dstID = Integer.parseInt(tokenizer.nextToken());
				
				Page src = site.getPageById(srcID);
				Page dst = site.getPageById(dstID);

				ArrayList<Page> dstArray;
				if (links.containsKey(src)) dstArray = links.get(src);
				else dstArray = new ArrayList<>();

				dstArray.add(dst);
				links.put(src, dstArray);

				link = linkData.readLine();

				count++;
			}
			System.out.println("finish readLine");

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (linkData != null) {
				try {
					linkData.close();
				} catch(IOException e) {}
			}
		}
		return links;
	}

}