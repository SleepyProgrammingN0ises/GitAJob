var prov = document.getElementById('prov');
var form = document.getElementsByTagName('form')[0];
var patronPWD = new RegExp('/[a-zA-Z]*|[0-9]*|[a-zA-Z]*[0-9]*|[0-9]*[a-zA-Z]*/');

var provs = ['A Coruña', 'Álava', 'Albacete', 'Alicante', 'Almería', 'Asturias', 'Ávila', 'Badajoz', 'Baleares', 'Barcelona', 'Burgos',
    'Cáceres', 'Cádiz', 'Cantabria', 'Castellón', 'Ciudad Real', 'Córdoba', 'Cuenca', 'Girona', 'Granada', 'Guadalajara',
    'Gipuzkoa', 'Huelva', 'Huesca', 'Jaén', 'La Rioja', 'Las Palmas', 'León', 'Lérida', 'Lugo', 'Madrid', 'Málaga',
    'Murcia', 'Navarra', 'Ourense', 'Palencia', 'Pontevedra', 'Salamanca', 'Segovia', 'Sevilla', 'Soria', 'Tarragona',
    'Sta. Cruz de Tenerife', 'Teruel', 'Toledo', 'Valencia', 'Valladolid', 'Vizcaya', 'Zamora', 'Zaragoza'];

for (let provi of provs) {
    let opt = document.createElement('option');
    let txt = document.createTextNode(provi);
    opt.append(txt);    //Le meto el texto (la provincia recogida) al option seleccionado
    prov.appendChild(opt);  //y después, la
}

function comprobarREGISTRO(e) {
    let errores = [], fails = document.getElementById('errors');
    let todoBien = true;
    let contrasena = document.getElementById('pass1');
    let contraRep = document.getElementById('pass2');

    if (fails.children.length != 0) {
        for (let i=0;i<fails.children.length;i++) {
            fails.children[i].remove();
        }
    }
    if(!patronPWD.test(contrasena.value)) {
        todoBien = false;
        errores.push('La contraseña debe tener: mínimo 1 letra, 1 número');
    }

    if (contrasena.value != contraRep.value) {
        todoBien = false;
        errores.push('ERROR - Las contraseñas no coinciden!');
    }

    if (errores.length != 0) {
        for (error of errores) {
            let list = document.createElement('li');
            let text = document.createTextNode(error);

            list.setAttribute('style', 'color: red; margin-left: 10px;');
            list.append(text);
            fails.appendChild(list);
        }
    }

    if (!todoBien) {
        e.preventDefault();
    } else {
        return true;
    }
}

function cambiarFondo(e) {
    e.target.style.backgroundColor = "#a4ffee";
}
function devolverFondo(e) {
    e.target.style.backgroundColor = "#ffffff";
}
let inputs = form.elements;

for (let i=0;i<inputs.length;i++) {
    if (inputs[i].tagName == 'INPUT') {
        inputs[i].addEventListener('focus', cambiarFondo);
        inputs[i].addEventListener('focusout', devolverFondo);
    }
}

form.addEventListener('submit', comprobarREGISTRO);