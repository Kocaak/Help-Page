package destekarama

import destekarama.veriyapilari.SayfaDuzeltmeNesnesi
import destekarama.veriyapilari.SayfaEklemeNesnesi;
import destekarama.veriyapilari.SayfaSilmeNesnesi

class AdminController {
	AramaService aramaService

	def sayfaEkle(SayfaEklemeNesnesi sn){
		aramaService.sayfaEkle(sn)
		render "eklendi"
	}
	
	def sayfaSil(SayfaSilmeNesnesi sn){
		aramaService.sayfaSil(sn)
		render "Silindi"
	}
	
	def sayfaDuzenle(SayfaDuzeltmeNesnesi sn){
		aramaService.sayfaDuzelt(sn)
		render "Duzenlendi"
	}
	
	def menuleriAl(){
		render aramaService.menuleriAl()
	}
}
