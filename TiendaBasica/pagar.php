<?php
session_start();
    if(isset($_REQUEST['pagar'])) {
        $_SESSION['pagar'] = true;
        unset($_SESSION['cesta']);
        echo "Pago realizado correctamente!<br>";
        echo "<a href='productos.php'>Volver a la tienda</a><br>";
        echo "<form action='index.php'>&nbsp;&nbsp;&nbsp;<button name='cerrar' id='cerrar' type='submit' value='cerrar'>Cerrar Sesion</button></form><br>";
    } else {
        header('Location: productos.php');
    }