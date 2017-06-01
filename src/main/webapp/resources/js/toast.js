

	//declaracion
	/*var toastWidget = {
			
		duration: 3000,
		
		show: function(text){
			
			console.debug('toastWidget:init(%s) %s ', this.duration, text);
			
		}
	};*/
	
	//declaracion
	var toastWidget = (function () {
		 
		//private
        var duration = 3000;
 
       function show( text ) {
        	console.debug('toastWidget:init(%s) %s ', duration, text);
        }
 
       //public
        return {
        	show : show
        };
 
    })();
	
	
	//usarlo
	toastWidget.show('pepe');
	
	//No se puede cambiar, es privado
	toastWidget.duration = 1000;
	toastWidget.show('pepe2');

	
	function show_toast(texto){
		console.debug('show_toast: ' + texto);
		
		var toast = document.getElementById('toast');
		toast.innerHTML = texto;
		toast.style.animation = "show 2s";
		toast.style.animationFillMode = "forwards";
		
		setTimeout(function(){
			toast.style.animation = "none";
		}, 3000);
	}
	
	window.onload = function(){
		//crear div#toast
		var element = document.createElement('div');
		element.id = 'toast';
		document.body.appendChild(element);
		console.debug('creado div#toast');
	}

