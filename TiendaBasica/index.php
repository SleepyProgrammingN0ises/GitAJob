<html lang="es">
<head>
    <style>
        .fildo {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }
        .txt1 {
            font-size: 18px;
            font-family: sans-serif, "Agency FB";
        }
        .resetbtn {
            font-size: 24px;
        }
        fieldset button {
            padding: 7px;
        }
        fieldset input {
            padding: 5px;
            size: 35px;
        }
    </style>
</head>

<?php
    session_start();
    $usuario = "admin";
    $contra = "1234";

    //Declaracion variables BD
    //Se lo asignamos a una variable de sesión
$_SESSION['prods'] = array(array("COD" => 101, "NOMBRE" => "Teclado", "PVP" => 25, "STOCK" => 5),//CAMBIADO no son asociativos los array 'externos'
    array("COD" => 102, "NOMBRE" => "Raton Inalambrico", "PVP" => 13, "STOCK" => 10),
    array("COD" => 103, "NOMBRE" => "Base Refrigeradora", "PVP" => 20, "STOCK" => 15),
    array("COD" => 104, "NOMBRE" => "Cargador Oficial HP", "PVP" => 114, "STOCK" => 10),
    array("COD" => 105, "NOMBRE" => "Gafas para Pantallas", "PVP" => 35, "STOCK" => 35),
    array("COD" => 106, "NOMBRE" => "Peine para perro", "PVP" => 10, "STOCK" => 50));

    if (!isset($_SESSION['usu']) && !isset($_SESSION['cont'])){
        echo "<h2>Bienvenidx a TodoInformatica!</h2>";
        echo "<p>Por favor, inicie sesión para entrar a la página</p>";

        echo "<br>";
        echo "<fieldset class='fildo' style='max-width: fit-content;'>";
        echo "<legend><b>Login</b></legend>";
        echo "<form action='index.php' method='post'>";
        echo "<label for='user'>Usuario: </label><input id='user' name='user' type='text'/><br>";
        echo "<br><label for='cont'>Contraseña: </label><input id='cont' name='cont' type='password'/>";
        echo "<br><br><button type='submit'>Entrar a <span class='txt1'>TODOINFORMATICA</span></button>
                <button class='resetbtn' type='reset'>♻</button>";
        echo "</form>";
        echo "</fieldset>";
        if(isset($_REQUEST['user']) && isset($_REQUEST['cont'])) {
            if ($_REQUEST['user'] == $usuario && $_REQUEST['cont'] == $contra) {
                $_SESSION['cerrar'] = false;    //Ultimo cambio
                $_SESSION['usu'] = $_REQUEST['user'];
                $_SESSION['cont'] = $_REQUEST['cont'];
                print "Sesion correcta! Redirigiendote...";
                header('Location: productos.php');
            } else {
                print "<script>alert('¡Combo usuario-contraseña incorrecta!');</script>";
            }
        }
    } else {    //Si hemos venido a esta pagina, y YA ESTA SETTEADO el usuario y contraseña, es que venimos de darle a 'Cerrar Sesión'
        //if (isset($_REQUEST['cerrar'])) {
            session_destroy();
            header('Location: index.php');
        //}
    }

    ?>
</html>
</html>
