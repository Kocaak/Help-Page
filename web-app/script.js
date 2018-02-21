function aramaFonksiyonu() {

	var aranacak = $('#scriptBox').val();
	if (aranacak != null) {
		aramaYap(aranacak);
		 $($(this).data("#tab6success")).modal('show');
	}
}
function aramaFonksiyonu1() {

	var aranacak = $('#scriptBox1').val();
	if (aranacak != null) {
		aramaYap(aranacak);
	}
}

var baslik;
var uzunicerik;
var ix;
var aramaSonuclari;

function aramaYap(aranacak){

	$.get("/destekarama/arama/icindekileriAl", {aramaMetni : aranacak},
			function(data, status) {
				aramaSonuclari = data.bulunanSayfalar;
				if (data.bulunanSayfalar.length != 0) {
					$("#arama").empty();
						for (i = 0; i < data.bulunanSayfalar.length; i++) {
							baslik = $(data.bulunanSayfalar).get(i).title;
							uzunicerik = $(data.bulunanSayfalar).get(i).icerik;
							ix = $(data.bulunanSayfalar).get(i).bulunanYer;
							var geciciicerik = uzunicerik.substring(ix-40, ix+70);
//							$("#arama").append('<div class="panel panel-info"><div id = "sonuc'+i+'" class="panel panel-info"><h4 class="panel-heading" id="aramaBaslik" style="color:000063;">'
//														+ baslik + ' </h4><p class="panel-body" style="color:000063;">...'
//														+ geciciicerik + '...</p></div></div>');
							
						
						var disDiv = document.createElement('div');
						//baslikDiv.id = 'block';
						disDiv.className = 'panel panel-info';
						
						var icDiv = document.createElement('div');
						//icDiv.id = 'block';
						icDiv.className = 'panel panel-info';
						
						disDiv.appendChild(icDiv);

						
						var h4Div = document.createElement('h4');
						h4Div.textContent = baslik;
						h4Div.id = 'aramaBaslik';
						h4Div.className = 'panel-heading';
						
						icDiv.appendChild(h4Div);
						
						
						var pDiv = document.createElement('p');
						pDiv.textContent = '...'+geciciicerik+'...';
						pDiv.className = 'panel-body icerikBilgileri';
						
						icDiv.appendChild(pDiv);
						
						
						var btnShow = document.createElement("button");
		                btnShow.setAttribute("type", "button");
		                btnShow.textContent = "Sayfaya Git";
		                btnShow.className = 'btn btn-danger sagayasla';
		                btnShow.onclick = (function(icrk) {
		                    return function() {
		                    	sayfayiAc(icrk);
							};
		                })(uzunicerik);

		                icDiv.appendChild(btnShow);
		                
		                $("#arama").append(disDiv);
		                
							// $("#arama
							// icerik").append('<icerik>'+data.bulunanicerik+'</icerik>');
							// $("baslik").append("<b>"+data.baslik+"</b>");
							// $("icerik").append(data.sayfaninicerigi);
							// alert("'Arama Sonucu' sekmesinden, sonuçları
							// görebilirsiniz.");
							console.log(data);
						}
						}

						else {
							// alert("Aradığınız "+aranacak+" kelimesiyle
							// ilgili bir şey bulamadık. Lütfen başka bir
							// kelime giriniz.");
							$("#arama").empty();
							$("#arama").append('<h2>Üzgünüz, '+ aranacak+ ' ile ilgili bir şey bulamadık. Belki farklı bir sözcük denemek istersiniz?</h2><div><input id="scriptBox1" onkeypress="return runScript1(event)" input type="text" name="search" placeholder="Ara..." ><button class="btn btn-primary btn-lg" type="button" onclick="return aramaFonksiyonu1()">Ara →</button></div>');
						}
					});
}

function sayfayiAc(deneme){
	
	$("#secilenIcerik").empty();
	$("#secilenIcerik").append(deneme);
	
}

function ekleFonksiyonu() {

	var tbaslik = $('#eklenecekBaslik').val();
	var ticerik = $('#eklenecekIcerik').val();
	if (tbaslik != null && ticerik != null) {
		$.get("/destekarama/admin/sayfaEkle", {
			ustMenuID : selectedNode.data.id,
			baslik: tbaslik,
			icerik: ticerik
		}, function(data, status) {
			menuYukle();
			$("#eklenecekBaslik").val('');
			$("#eklenecekIcerik").val('');
		});
	}
}

function duzenlemeFonksiyonu() {

	var tbaslik = $('#duzenlenecekBaslik').val();
	var ticerik = $('#duzenlenecekIcerik').val();
	if (tbaslik != null && ticerik != null) {
		$.get("/destekarama/admin/sayfaDuzenle", {
			id : selectedNode.data.id,
			baslik: tbaslik,
			icerik: ticerik
		}, function(data, status) {
			menuYukle();
		});
	}
	$("#duzenlenecekBaslik").val('');
	$("#duzenlenecekIcerik").val('');
}

function silmeFonksiyonu() {
	if (selectedNode.data.id != null) {
		$.get("/destekarama/admin/sayfaSil", {
			id : selectedNode.data.id
		}, function(data, status) {
			menuYukle();
		});
	}
}

function bosluguDoldur() {
		$('#duzenlenecekBaslik').val('');
		$("#duzenlenecekIcerik").val('');
		$('#duzenlenecekBaslik').val(selectedNode.title);
		console.log(selectedNode.title);
		console.log(selectedNode.data.icerik);
		$('#duzenlenecekIcerik').val(selectedNode.data.icerik);
}

function runScript(e) {
	if (e.keyCode == 13) {
		aramaFonksiyonu();

	}
}

function runScript1(e) {
	if (e.keyCode == 13) {
		aramaFonksiyonu1();
	}
}

$('#iconbar a').click(function(e) {
	e.preventDefault();
	$('#iconbar a').removeClass('active');
	$(this).addClass('active');
});

var selectedNode;

$(function() {
	
	menuYukle();
	
	
//	$.get(
//			"/destekarama/arama/menuleriAl",
//			function(data, status) {
//				
//				console.log(data);
//				
//
//			});


	
});

function menuYukle() {
	$("#tree").addClass('agac');
$("#tree").fancytree({
	source : { url: "/destekarama/arama/menuleriAl" },
	expanded: true,
	icon: false,
	checkbox: false,
	activate: function(event, data) {
	selectedNode = data.tree.getActiveNode();

	$("#secilenIcerik").empty();
	console.log(data);
	$("#secilenIcerik").append(selectedNode.data.icerik);
}
});
}