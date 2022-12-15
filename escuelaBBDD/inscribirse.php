<head>
    <link rel="stylesheet" href="./Vistas/login.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
</head>

<link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sticky-footer/">
<?php
    require_once "./Vistas/cabecera0.php";
    require_once "./conexionBD.php";
    require_once "./Modelos/Usuario.php";
    require_once "./Modelos/controladorAdmin.php";

    echo "<br><div class='registro1'>
          <fieldset>
            <legend>Introduce tus datos!</legend>
            <hr>
            <form action='' method='post'>
                <table class='regLogin'>
                    <tbody>
                        <tr>
                            <td colspan='2'>
                                <div class='inputFile'>";
                            echo "<img id='img1' src='' alt='fotoArchivo'/>
                                <input type='file' id='file' name='foto1' oninput='imgfuncion()'/>
                                </div>
                                
                            </td>
                        </tr>
                        <tr>
                            <td><label for='nom'>Nombre*: </label></td>
                            <td><input type='text' id='nom' name='nomb' required/></td>
                        </tr>
                        <tr>
                            <td><label for='apel'>Apellido(s): </label></td>
                            <td><input type='text' id='apel' name='apel'/></td>
                        </tr>
                        <tr>
                            <td><label for='email'>E-mail*: </label></td>
                            <td><input type='email' id='email' name='email' required/></td>
                        </tr>
                        <tr>
                            <td><input type='password' id='pass' name='pass1' placeholder='Contraseña*' required aria-required='true'/></td>
                            <td><input type='password' id='pass' name='pass2' placeholder='Repetir contraseña*' required aria-required='true'/></td>
                        </tr>
                        <tr>
                            <td><label for='direc'>Dirección*: </label></td>
                            <td><input type='text' id='direc' name='direccion' required aria-required='true'/></td>
                        </tr>
                        <tr>
                            <td><label for='local'>Localidad*: </label></td>
                            <td><input type='text' id='local' name='localidad' required aria-required='true'></td>
                        </tr>
                        <tr>
                            <td><input placeholder='C.Postal' id='cPost' name='codP' type='text' required aria-required='true'/></td>
                            <td><select id='prov' name='provi' required aria-required='true'>
                            <option value='def' selected>--PROVINCIA--</option>
                            </select></td>
                        </tr>
                        <tr>
                            <td><label for='tlf'>Teléfono*: </label></td>
                            <td><input type='number' id='tlf' name='telef' required aria-required='true' min='0' max='999999999'></td>
                        </tr>
                        <tr>
                            <td><label for='NIF'>NIF/DNI: </label></td>
                            <td><input type='text' id='NIF' name='DNI' PLACEHOLDER='XXXXXXXX-A' required aria-required='true' minlength='10' maxlength='10'></td> <!--longitud de 10 por XXXXXXXX-W-->
                        </tr>
                    </tbody>
                    <br>
                    <tr>
                        <td colspan='2'><button type='submit' name='registro' value='true'>Registrarse!</button></td>
                    </tr>
                    <tr>
                        <td><button type='reset'>Limpiar campos</button></td>
                        <td><p class='peq'>*Campos obligatorios</p></td>
                    </tr>
                </table>
            </form>
          </fieldset>
</div>";

if (isset($_REQUEST['registro'])) {
        $datos = [$_REQUEST['nomb'], $_REQUEST['apel'], $_REQUEST['email'], $_REQUEST['pass1'], $_REQUEST['direccion'],
            $_REQUEST['localidad'], $_REQUEST['provi'], $_REQUEST['telef'], $_REQUEST['codP'], $_REQUEST['DNI']];

        $cnx = conexionBD::getConexion();
        if (crearUsuario($datos, $cnx)) {
            $_SESSION['errors'] = "Usuario CREADO correctamente! Creado como Admin.";
        } else {
            $_SESSION['errors'] = "Error al crear el Usuario!";
        }
}

if (isset($_SESSION['email'])) {
    //si hemos entrado ya
    header('Location: ./Vistas/escuela.php');
}
    echo "<script>
    function imgfuncion() {

        var file = document.getElementById('file').files[0];
        var reader  = new FileReader();
        // it's onload event and you forgot (parameters)
        reader.onload = function(e)  {
            var image = document.createElement('img');
            image.setAttribute('width', '100px');
            image.setAttribute('height', 'auto');
            image.src = e.target.result;
            document.getElementById('img1').replaceWith(image);
         }
         // you have to declare the file loading
         reader.readAsDataURL(file);
    }
    </script>";
    echo "<script src='registro.js'></script>";
    require_once "./Vistas/footer.php";