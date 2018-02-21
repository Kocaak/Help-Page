package destekarama

import java.util.ArrayList;

import grails.converters.JSON
import grails.transaction.Transactional

import org.codehaus.groovy.grails.web.context.ServletContextHolder

import destekarama.veriyapilari.AramaParametreleri
import destekarama.veriyapilari.AramaSonucu
import destekarama.veriyapilari.SayfaDuzeltmeNesnesi
import destekarama.veriyapilari.SayfaEklemeNesnesi
import destekarama.veriyapilari.SayfaSilmeNesnesi
import destekarama.veriyapilari.YardimSayfasi

@Transactional
class AramaService {

	YardimSayfasi kokYardimSayfasi = new YardimSayfasi()
	HashMap<String, YardimSayfasi> idYeGoreSayfalar = new HashMap()

	public void testDosyaOlustur(){

		YardimSayfasi kys = new YardimSayfasi(title : "Kök Menü",icerik: "-----", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID())

		YardimSayfasi pb = new YardimSayfasi(title : "Popüler Başlıklar",icerik: "1aaa", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID())

		YardimSayfasi deneme = new YardimSayfasi(title : "Deneme",icerik: "7aaa", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID())

		pb.children.add(deneme)

		kys.children.add(pb)

		//		kys.children.add(new YardimSayfasi(title : "Yönetimsel İşlemler",icerik: "2aaa", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID()))
		//		kys.children.add(new YardimSayfasi(title : "Kullanıcı İşlemleri",icerik: "3aaa", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID()))
		//		kys.children.add(new YardimSayfasi(title : "Diğer İşlemler",icerik: "4aaa", children:new ArrayList<YardimSayfasi>(), id:UUID.randomUUID()))

		String jsonIndex = new JSON(kys)

		//		def converter = new JSON(target: kys);

		File file = new File(ServletContextHolder.getServletContext().getRealPath("dosyalar/index.json"))

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile()
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF8"))
		out.append(jsonIndex)

		out.flush()
		out.close()
	}

	public void dosyayiOku(){

		File file = new File(ServletContextHolder.getServletContext().getRealPath("dosyalar/index.json"))

		if (file.exists()) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
					new FileInputStream(file), "UTF8"))

			String str;
			String tumYazi = ""

			while ((str = br.readLine()) != null) {
				tumYazi += str
			}
			kokYardimSayfasi = JSON.parse(tumYazi)
			idYeGoreSayfalar.clear()
			altMenuleriGezin(kokYardimSayfasi)
			br.close();
		}
	}

	public void dosyayaYaz(){
		String jsonIndex = new JSON(kokYardimSayfasi)

		//		def converter = new JSON(target: kys);

		File file = new File(ServletContextHolder.getServletContext().getRealPath("dosyalar/index.json"))

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile()
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file), "UTF8"))
		out.append(jsonIndex)

		out.flush()
		out.close()
	}

	private altMenuleriGezin(YardimSayfasi ys){
		idYeGoreSayfalar.put(ys.id, ys)
		if(ys.children != null && ys.children.size() > 0){
			for(YardimSayfasi ysDongu : ys.children){
				altMenuleriGezin(ysDongu)
			}
		}
	}
	
//	private YardimSayfasi altMenuBul(YardimSayfasi ys, String mID){
//		if(ys.id.equals(mID)){
//			return ys
//		}else{
//			if(ys.children != null && ys.children.size() > 0){
//				for(YardimSayfasi ysDongu : ys.children){
//					YardimSayfasi sonuc = altMenuBul(ysDongu, mID)
//					if(sonuc != null){
//						return sonuc
//					}
//				}
//			}
//			return null
//		}
//		
//		
//	}

	public String menuleriAl(){
		if(kokYardimSayfasi == null || kokYardimSayfasi.children == null){
			dosyayiOku()
		}
		List<YardimSayfasi> kokMenuler = new ArrayList<YardimSayfasi>()
		kokMenuler.add(kokYardimSayfasi)

		return kokMenuler as JSON
	}

	public void sayfaEkle(SayfaEklemeNesnesi sn){
		String ustMenuID = sn.ustMenuID
		
		YardimSayfasi ysYeniMenu = new YardimSayfasi()

		String yeniID = UUID.randomUUID()
		ysYeniMenu.id = yeniID
		ysYeniMenu.children = new ArrayList<YardimSayfasi>()
		ysYeniMenu.icerik = sn.icerik
		ysYeniMenu.title = sn.baslik
		ysYeniMenu.ustID = ustMenuID
		idYeGoreSayfalar.put(yeniID, ysYeniMenu)

		YardimSayfasi ustMenu = idYeGoreSayfalar.get(ustMenuID)
		ustMenu.children.add(ysYeniMenu)

		dosyayaYaz()

	}
	
	public void sayfaSil(SayfaSilmeNesnesi sn){
		
		String menuID = sn.id
		YardimSayfasi menu = idYeGoreSayfalar.get(menuID)
		
		String ustMenuID = menu.ustID
		YardimSayfasi ustMenu = idYeGoreSayfalar.get(ustMenuID)
		
		ustMenu.children.remove(menu)

		dosyayaYaz()

	}
	
	public void sayfaDuzelt(SayfaDuzeltmeNesnesi sn){
		
		altMenuleriGezin(kokYardimSayfasi)
		
		String baslik = sn.baslik
		String icerik = sn.icerik
		String menuID = sn.id
		YardimSayfasi menu = idYeGoreSayfalar.get(menuID)
		
		menu.title = baslik
		menu.icerik = icerik

		dosyayaYaz()

	}

	public String mapOku(){
		return idYeGoreSayfalar as JSON
	}
	
	
	public AramaSonucu aramaIslemi(AramaParametreleri ap){
		
		AramaSonucu armsonuc = new AramaSonucu()
		ArrayList<YardimSayfasi> bs = new ArrayList<YardimSayfasi>()
		armsonuc.bulunanSayfalar = bs
		
		for (YardimSayfasi sayfa : idYeGoreSayfalar.values()) {
			
			if(sayfa.icerik.indexOf(ap.aramaMetni) != -1){
				
				sayfa.bulunanYer = sayfa.icerik.indexOf(ap.aramaMetni)
				bs.add(sayfa)
				
			}
			
			else if(sayfa.title.indexOf(ap.aramaMetni) != -1){
				sayfa.bulunanYer = 0
				bs.add(sayfa)
			}
			
			else{
				println "Error"
			}
			
		}
		
		return armsonuc
		
	}
	
	

}
