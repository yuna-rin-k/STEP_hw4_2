//未完成

/*targetPageを見ている人におすすめのページ
 *ReccomendPageの定義: 
 *①ページ内のリンクがどれだけtargetPageとかぶっているかの割合が高いもの
 *targetPage's links : a,b,c,d,e
 *A's links : a,b,x
 *B's links : a,b,c,x,y,z
 *Aの方がtargetPageとかぶっている割合が高いからAがreccomendされる
 */
import java.util.*;
public class ReccomendPage {

	public static void main(String[] args) {

		LoadPageInf pageInf = new LoadPageInf("pages.txt", "links.txt");
		ArrayList<Page> pages = pageInf.getPages();
		Site site = new Site(pages);
		Map<Page, ArrayList<Page>> links = pageInf.getLinks(site);
		site.setLinks(links);
		System.out.println();
		System.out.println();
		System.out.println("finish load");
		Page targetPage = site.getPageByPageName("Google");
		//ArrayList<Page> reccomendPages = findReccomend_Patern1(targetPage, pages, links);
		Map<Integer, Page> rank =findReccomend_Patern1(targetPage, pages, links);
		printRank(rank);
	}

	static TreeMap<Integer, Page> findReccomend_Patern1(Page targetPage, ArrayList<Page> pages, Map<Page, ArrayList<Page>> links) {

		TreeMap<Integer, Page> rank = new TreeMap<>(Collections.reverseOrder());
		ArrayList<Page> linksOfTargetPage = links.get(targetPage);
		double maxDeplicateRate = 0;

		for (int i = 0; i < 5; i++) {
			Page pageFive = pages.get(i);
			rank.put(0,pageFive);
		}
		Page rankFive = pages.get(0);

		for (int i = 0; i < pages.size(); i++) {

			Page page = pages.get(i);

			if (!links.containsKey(page) || page.equals(targetPage))
				continue;

			ArrayList<Page> pageLinks = links.get(page);
			int numOfdeplication = 0;

			for (int j = 0; j < pageLinks.size(); j++) {
				Page link = pageLinks.get(j);
				if (linksOfTargetPage.contains(link)) 
					numOfdeplication++;
			}
			//System.out.println(numOfdeplication);
			if (numOfdeplication > rank.firstKey()) {
				rank.put(numOfdeplication, rankFive);
			}
		}
		return rank;
	}

	static void printRank(Map<Integer, Page> ranking) {

		for (Page rankPage : ranking.values()) {
			System.out.println(rankPage.pageName);
		}
	}
}