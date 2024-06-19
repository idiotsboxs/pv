
function selectFocus(obj)
{
	alert(obj);
	document.getElementById(obj).value='';
	document.getElementById(obj).focus();
alert('ya');
}

function confirmSale() {
	var r = confirm("¿Aprobar venta/Compra?");
	return r;
}

function confirmCierre() {
	var r = confirm("Se cerrara la venta del día de hoy \n ¿desa continuar?");
	return r;
}

function numericNull(obj) {

	var value = obj.value.trim();
	if (value.length == 0) {
		obj.value = 0;
	}

}


function validaBlur(obj) {

	var value = obj.value.trim();
	if (obj.value == 0) {
		return false;
	}else{
		return true;
	}

}


function decimalInput(obj) {
	var value = obj.value;
	var numeric = ""
	for (index = 0; index < value.length; index++) {

		if (isNaN(value[index]) && value[index] != '.') {
			numeric.concat('');
		} else {
			numeric = numeric.concat(value[index]);
		}

	}
	obj.value = parseFloat(Math.round(numeric * 100) / 100).toFixed(2);
}

function numericInput(obj) {
	var value = obj.value;
	var numeric = ""
	for (index = 0; index < value.length; index++) {

		if (isNaN(value[index])) {
			numeric.concat('');
		} else {
			numeric = numeric.concat(value[index]);
		}

	}
	obj.value = numeric;
}


function SetArticulo(obj) {
	
    if (window.opener != null && !window.opener.closed) {
        var txtId = window.opener.document.getElementById("form:first");
        txtId.value = obj;
        txtId.focus();
       
        txtId.value = txtId.value.trim();
        
    }
    window.close();
}


function loadCenterLayout(){

	document.getElementById("subLayout").className = "alignDiv";
	document.getElementById("subLayout").removeAttribute("style");
	document.getElementById("form:first").focus();

	
}

function loadCenterLayoutDos(){

	document.getElementById("form:first").focus();

	
}

function clickButton(e,button) {
    if (e.keyCode == 13) {    	
    	document.getElementById('form:first').value = '0';
    	document.getElementById(button).click();   
    	
    }
}

function focusEliminar(id){

	if(document.getElementById(id).value == '1'){
		document.getElementById(id).value = '0'
		document.getElementById('form:first').focus();
	}
}
