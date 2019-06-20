import java.util.*;
import java.io.*;

public class PageRankInWiki {
	public static void main(String[] args) {

		LoadPageInf pageInf = new LoadPageInf("pages.txt", "links.txt");

		ArrayList<Page> pages = pageInf.getPages();
		Site site = new Site(pages);
		Map<Page, ArrayList<Page>> links = pageInf.getLinks(site);
		site.setLinks(links);
		Page maxValPage = decideRank(site, pages, links);
		System.out.println("NO.1 Page is "+ maxValPage.pageName);
	}

	static Page decideRank(Site site, ArrayList<Page> pages, Map<Page, ArrayList<Page>> links) {

		int initVal = 100;
		int randomValue = 15;
		int equalVal = initVal - randomValue;

		double[] currentVal = new double[pages.size()];
		double[] nextVal = new double[pages.size()];
		Arrays.fill(currentVal, initVal);
		Arrays.fill(nextVal, 0);

		int count = 0;
		while (count < 5) {

			calculotorVal(pages, links, currentVal, nextVal, randomValue);

			swap(currentVal, nextVal);
			Arrays.fill(nextVal, 0);
			count++;
		}

		double maxVal = 0;
		int maxValPageID = -1;
		for (int i = 0; i < currentVal.length; i++) {
			double val = currentVal[i];
			if (val > maxVal) {
				maxVal = val;
				maxValPageID = i;
			}
		}
		Page maxValPage = site.getPageById(maxValPageID);

		return maxValPage;
	}

	static void calculotorVal(ArrayList<Page> pages, Map<Page, ArrayList<Page>> links, 
												double[] currentVal, double[] nextVal, int randomValue) {

		for (int i = 0; i < pages.size(); i++) {

			Page page = pages.get(i);
			nextVal[page.id] += randomValue;

			if (!links.containsKey(page))
				continue;

			ArrayList<Page> linkPeges = links.get(page);
			double distributeVal = currentVal[page.id] / linkPeges.size();

			for (int j = 0; j < linkPeges.size(); j++) {
				Page linkPage = linkPeges.get(j);
				nextVal[linkPage.id] += distributeVal;
			}
		}
	}
	static ArrayList<Page> decideRankWithOption() {

		return null;
	}
	static void swap(double[] x, double[] y) {

		if (x.length != y.length) return;

		for (int i = 0; i < x.length; i++) {
			double temp = x[i];
			x[i] = y[i];
			y[i] = temp;
		}
	}
}