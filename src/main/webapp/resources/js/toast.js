	var toastWidget = {
		
		duration: 3000,
		
		show: function(text){
			console.debug('toastWidget:init(%s) %s',this.duration, text );
		}
		
	};

	//usarlo
	
	toastWidget.show('pepe');
	
	toastWidget.duration = 1000;
	toastWidget.show('pepe2');
	
	function show_toast(texto){
		console.debug('show_toast: ' + texto);
		var toast = document.getElementById('toast');
		toast.style.animation = "show 2s";
		toast.style.animationFillMode = "forwards";
		toast.innerHTML = texto;
		setTimeout(function(){
			toast.style.animation = "none";
		}, 3000);
	}
	
	window.onload = function(){
		var element = document.createElement('div');
		element.id = 'toast';
		document.body.appendChild(element);
		console.debug('creado div#toast');
	}