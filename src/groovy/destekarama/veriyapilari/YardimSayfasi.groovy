package destekarama.veriyapilari

class YardimSayfasi {
	String title
	String icerik
	String id
	String ustID
	int bulunanYer
	
	List<YardimSayfasi> children
	
	boolean expanded = true
	
	@Override
	public boolean equals(Object o) {
		try{
			YardimSayfasi oBookdetails = (YardimSayfasi)o;
			return (this.id == oBookdetails.id);
		} catch (Exception e){
			//println e
			return false;
		}
	}
}
