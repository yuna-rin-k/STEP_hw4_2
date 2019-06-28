import java.util.*;
import java.io.*;

public class PageRankInWiki {
	public static void main(String[] args) {

		Site site = LoadPageInfo.loadSite("pages.txt", "links.txt");
		Page maxValPage = decideRank(site);

		System.out.println("NO.1 Page is " + maxValPage.pageName);
		
	}

	static Page decideRank(Site site) {

		ArrayList<Page> pages = site.getPages();
		Map<Page, ArrayList<Page>> links = site.getLinks();

		double initVal = 100;
		double randomValueRate = 0.15;
		double randomValue = randomValueRate * 100;
		double equalVal = initVal - randomValue;

		double[] currentVal = new double[pages.size()];
		double[] nextVal = new double[pages.size()];
		Arrays.fill(currentVal, initVal);
		Arrays.fill(nextVal, randomValue);

		while (true) {

			calculateVal(pages, links, currentVal, nextVal, randomValueRate);

			if (isConverge(currentVal, nextVal))
				break;

			swap(currentVal, nextVal);
			Arrays.fill(nextVal, randomValue);
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

	static void calculateVal(ArrayList<Page> pages, Map<Page, ArrayList<Page>> links, 
								double[] currentVal, double[] nextVal, double randomValueRate) {

		for (int i = 0; i < pages.size(); i++) {

			Page page = pages.get(i);

			ArrayList<Page> linkPages = links.get(page);

			if (linkPages == null) {
				nextVal[page.id] += (currentVal[page.id] * (1-randomValueRate));
				continue;
			}

			double distributeVal = currentVal[page.id] * (1-randomValueRate) / linkPages.size();

			for (int j = 0; j < linkPages.size(); j++) {
				Page linkPage = linkPages.get(j);
				nextVal[linkPage.id] += distributeVal;
			}
		}
	}

	static boolean isConverge(double[] currentVal, double[] nextVal) {

		double delta = 5.0;
		double diff_square = 0.0;

		for (int i = 0; i < currentVal.length; i++) {
			double c_i = currentVal[i];
			double n_i = nextVal[i];
			diff_square += (n_i - c_i) * (n_i - c_i);
		}

		double diff = Math.sqrt(diff_square/currentVal.length);

		return diff <= delta;
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