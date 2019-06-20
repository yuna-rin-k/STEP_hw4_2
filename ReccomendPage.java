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

		System.out.println("finish load");
		Page targetPage = site.getPageByPageName("Google");
		Page reccomendPage = findReccomend_Patern1(targetPage, pages, links);
		System.out.println(reccomendPage.pageName);
	}

	static Page findReccomend_Patern1(Page targetPage, ArrayList<Page> pages, Map<Page, ArrayList<Page>> links) {

		ArrayList<Page> linksOfTargetPage = links.get(targetPage);

		Page reccomendPage = null;
		double maxDeplicateRate = 0;
		
		for (int i = 0; i < pages.size(); i++) {

			Page page = pages.get(i);

			if (!links.containsKey(page) || page.equals(targetPage))
				continue;

			ArrayList<Page> pageLinks = links.get(page);
			double numOfdeplication = 0;

			for (int j = 0; j < pageLinks.size(); j++) {
				Page link = pageLinks.get(j);
				if (linksOfTargetPage.contains(link)) 
					numOfdeplication++;
			}
			double deplicateRate = numOfdeplication / pageLinks.size();
			if (maxDeplicateRate < deplicateRate) {
				maxDeplicateRate = deplicateRate;
				reccomendPage = page;
			}
		}
		return reccomendPage;
	}
}