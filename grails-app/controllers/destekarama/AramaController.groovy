package destekarama

import grails.converters.JSON

import org.codehaus.groovy.grails.web.context.ServletContextHolder

import destekarama.veriyapilari.AramaParametreleri
import destekarama.veriyapilari.AramaSonucu
import destekarama.veriyapilari.SayfaEklemeNesnesi

class AramaController {
	AramaService aramaService
	
	def menuleriAl(){
		render aramaService.menuleriAl()
	}
	
    def icindekileriAl(AramaParametreleri ap) {
		
//		String s = request.JSON
//		
//		AramaParametreleri ap = new AramaParametreleri(JSON.parse(s))
		
//		String rootPath = ServletContextHolder.getServletContext().getRealPath("metinDosyalari/1.txt")
//		AramaSonucu sonuc =  a.destekarama(rootPath, ap.aramaMetni)
		AramaSonucu sonuc =  aramaService.aramaIslemi(ap)
		
//		AramaSonucu armsnc = new AramaSonucu(JSON.parse())
		
		render sonuc as JSON
		
	}
	
	
	
	def test(){
		aramaService.testDosyaOlustur()
		render "a"
	}
	
	def test2(){
		aramaService.dosyayiOku()
		render "b"
	}
	
	def test3(){
		render aramaService.mapOku()
	}
}
