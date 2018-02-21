package destekarama;

import destekarama.veriyapilari.AramaSonucu
import java.util.logging.Level
import java.util.logging.Logger


public class Arama {
	
	ArrayList<String> bulunanbaslik = new ArrayList<String>();
	ArrayList<String> bulunanicerik = new ArrayList<String>();
    String sayfa1icerik = ""
	String sayfa2icerik = ""
	String sayfa3icerik = ""
	String sayfa4icerik = ""
	String sayfa1baslik = ""
	String sayfa2baslik = ""
	String sayfa3baslik = ""
	String sayfa4baslik = ""
	String aranacakBuyukHarf = " ";
	String aranacakKucukHarf = " ";
    int sayfa1sayi,sayfa2sayi,sayfa3sayi,sayfa4sayi;
	String replaceAll;
	private BufferedReader reader1;
	private BufferedReader reader2;
	private BufferedReader reader3;
	private BufferedReader reader4;
	int bulundumu;


//	public AramaSonucu destekarama(String aramaDosyaAdi, String aranacak)
	public AramaSonucu destekarama(String aranacak,String dosya1,String dosya2,String dosya3,String dosya4)
	{
		aranacakBuyukHarf = aranacak.toUpperCase(new Locale("tr_TR")); //(new Locale("tr_TR") (Locale.ENGLISH);
		aranacakKucukHarf = aranacak.toLowerCase(new Locale("tr_TR"));
		
		sayfa1sayi = 0;
		sayfa2sayi = 0;
		sayfa3sayi = 0;
		sayfa4sayi = 0;
		bulundumu = 0;
		
		File file1 = new File(dosya1);
		File file2 = new File(dosya2);
		File file3 = new File(dosya3);
		File file4 = new File(dosya4);
		
		reader1 = new BufferedReader(new FileReader(file1));
		reader2 = new BufferedReader(new FileReader(file2));
		reader3 = new BufferedReader(new FileReader(file3));
		reader4 = new BufferedReader(new FileReader(file4));
		
		String satir;
		
		satir = reader1.readLine()
		sayfa1baslik = satir
		
            while((satir = reader1.readLine()) != null){
                replaceAll=null;
                replaceAll = satir.replaceAll(" ", "&&&");
                String[] denek = replaceAll.split("&&&");
                
                for(int i = 0;i < denek.length; i++){
					sayfa1icerik += denek[i]+" "
                    sayfa1sayi++;
                }
            }
			
			satir = reader2.readLine()	
			sayfa2baslik = satir
			
			while((satir = reader2.readLine()) != null){
				replaceAll=null;
				replaceAll = satir.replaceAll(" ", "&&&");
				String[] denek = replaceAll.split("&&&");
				
				for(int i = 0;i < denek.length; i++){
					sayfa2icerik += denek[i]+" "
					sayfa2sayi++;
				}
			}
			
			satir = reader3.readLine()
			sayfa3baslik = satir
			
			while((satir = reader3.readLine()) != null){
				replaceAll=null;
				replaceAll = satir.replaceAll(" ", "&&&");
				String[] denek = replaceAll.split("&&&");
				
				for(int i = 0;i < denek.length; i++){
					sayfa3icerik += denek[i]+" "
					sayfa3sayi++;
				}
			}
			
			satir = reader4.readLine()
			sayfa4baslik = satir
			
			while((satir = reader4.readLine()) != null){
				replaceAll=null;
				replaceAll = satir.replaceAll(" ", "&&&");
				String[] denek = replaceAll.split("&&&");
				
				for(int i = 0;i < denek.length; i++){
					sayfa4icerik += denek[i]+" "
					sayfa4sayi++;
				}
			}
            
			System.out.println(sayfa1icerik)
			System.out.println(sayfa2icerik)
			System.out.println(sayfa3icerik)
			System.out.println(sayfa4icerik)
			
        
		//C:\PROJECTS\destekarama\web-app\metinDosyalari\1.txt
		
		AramaSonucu armsnc = new AramaSonucu()
		
                    if(aranacakBuyukHarf != null && aranacakKucukHarf != null && sayfa1icerik != null && sayfa1icerik.indexOf(aranacakBuyukHarf) != -1){
                    	System.out.println("ARADIĞINIZ KELİME OLAN '"+aranacakBuyukHarf+"' POPÜLER BAŞLIKLARDA ELE ALINMIŞTIR.");
						bulundumu++;
//						armsnc.baslik = sayfa1baslik
//						armsnc.sayfaninicerigi = sayfa1icerik
						bulunanbaslik.add(sayfa1baslik)
						bulunanicerik.add(sayfa1icerik)
						armsnc.bulunanbaslik = bulunanbaslik
						armsnc.bulunanicerik = bulunanicerik
						armsnc.bulundumu = bulundumu
					}
					
					if(aranacakBuyukHarf != null && aranacakKucukHarf != null && sayfa2icerik != null && sayfa2icerik.indexOf(aranacakBuyukHarf) != -1){
						System.out.println("ARADIĞINIZ KELİME OLAN '"+aranacakBuyukHarf+"' YÖNETİMSEL İŞLEMLERDE ELE ALINMIŞTIR.");
						bulundumu++;
//						armsnc.baslik = sayfa2baslik
//						armsnc.sayfaninicerigi = sayfa2icerik
						bulunanbaslik.add(sayfa2baslik)
						bulunanicerik.add(sayfa2icerik)
						armsnc.bulunanbaslik = bulunanbaslik
						armsnc.bulunanicerik = bulunanicerik
						armsnc.bulundumu = bulundumu
					}
					
					if(aranacakBuyukHarf != null && aranacakKucukHarf != null && sayfa3icerik != null && sayfa3icerik.indexOf(aranacakBuyukHarf) != -1){
						System.out.println("ARADIĞINIZ KELİME OLAN '"+aranacakBuyukHarf+"' KULLANICI İŞLEMLERDE ELE ALINMIŞTIR.");
						bulundumu++;
//						armsnc.baslik = sayfa4baslik
//						armsnc.sayfaninicerigi = sayfa4icerik
						bulunanbaslik.add(sayfa3baslik)
						bulunanicerik.add(sayfa3icerik)
						armsnc.bulunanbaslik = bulunanbaslik
						armsnc.bulunanicerik = bulunanicerik
						armsnc.bulundumu = bulundumu
					}
					
					if(aranacakBuyukHarf != null && aranacakKucukHarf != null && sayfa4icerik != null && sayfa4icerik.indexOf(aranacakBuyukHarf) != -1){
						System.out.println("ARADIĞINIZ KELİME OLAN '"+aranacakBuyukHarf+"' DİĞER İŞLEMLERDE ELE ALINMIŞTIR.");
						bulundumu++;
//						armsnc.baslik = sayfa4baslik
//						armsnc.sayfaninicerigi = sayfa4icerik
						bulunanbaslik.add(sayfa4baslik)
						bulunanicerik.add(sayfa4icerik)
						armsnc.bulunanbaslik = bulunanbaslik
						armsnc.bulunanicerik = bulunanicerik
						armsnc.bulundumu = bulundumu
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					return armsnc	
}
}