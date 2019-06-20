import java.util.*;
import java.io.*;

public class PageRankInWiki {
	public static void main(String[] args) {
		for (int i = 0; i < 55000000; i++) {
			System.out.println(i);
		}
	}
		/*
		LoadPageInf pageInf = new LoadPageInf("pages.txt", "links.txt");

		ArrayList<Page> pages = pageInf.getPages();
		Site site = new Site(pages);
		Map<Page, ArrayList<Page>> links = pageInf.getLinks(site);
		decideRank(pages, links, 15);


	}

	static ArrayList<Page> decideRank(ArrayList<Page> pages, Map<Page, ArrayList<Page>> links, int randPacent) {

		double randomValue = pages.size() * randPacent * 0.001;
		double[] values = new double[pages.size()];

		System.out.println("RANDOM  "+randomValue);
		return null;
	}
*/
}