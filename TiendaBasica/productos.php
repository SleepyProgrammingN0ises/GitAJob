<html> <!--Este fichero es login-correcto.php pero renombrado-->
<head>
    <meta charset="UTF-8">
    <title>TODO INFORMATICA</title>
    <link rel="stylesheet" href="tienda.css"/>
    <style>
        table, tr, th, td {
            padding:2px;
            border: 2px solid gray;
            border-collapse: collapse;
        }
        th {
            font-size: 24px;
        }
        select {
            padding: 5px;
        }
    </style>
</head>
<form>
<?php

    session_start();

    if (!isset($_REQUEST['error']) && !isset($_REQUEST['cerrar'])) {    //Si aun no hemos lanzado error o cerrado sesion
        if (isset($_REQUEST['vaciar'])) {
            unset($_SESSION['cesta']);  //Con esto, eliminamos todo lo que hay en cesta
        }
        echo "<b>Bienvenido/a, " . $_SESSION['usu'] . "!!</b>";
        if (!isset($_SESSION['cesta'])) {   //Si no hemos creado la cesta
            $_SESSION['cesta'] = array();   //Se crea el array nuevo, para recoger los productos
            $_SESSION['cont'] = 0;
            echo "<br><p>Cesta vacía...</p>";
        } else {
            if (isset($_POST['COD'])) {   //Entendiendo que recibimos el STRING del codigo de los hidden inputs
                $_SESSION['contador'] = 1;
                if (isset($_SESSION['cesta']) && !empty($_SESSION['cesta'])) {   //Contador de productos en la cesta
                    foreach ($_SESSION['cesta'] as $item => $value) {
                        $_SESSION['contador']+= $value;
                    }
                } else {
                    $_SESSION['contador'] = 1;
                }
                $codigo = $_REQUEST['COD'];
                $nombre = $_REQUEST['NOMBRE'];
                $pvp = $_REQUEST['PVP'];
                $cantidad = 1;
                $i = -1; //Contador para objetos cesta; por cada vuelta en la primera dimension, añadimos uno al contador
//TODO: scrapear to esto, y meter los valores mediante un HIDDEN INPUT dentro del foreach, al crear la tabla --HECHO--
                foreach ($_SESSION['prods'] as $prods) {
                    $i++;
                        foreach ($prods as $clave => $valor) {
                            if ($valor == $codigo) {    //Comprobamos con este if que EXISTE en el vector de productos (ya que coinciden el codigo recogido y el del array)
                                if ($_SESSION['prods'][$i]["STOCK"] > 0) {    //Y después, comprobamos que HAY productos en stock
                                    if (isset($_SESSION['cesta'][$codigo])) {
                                        $_SESSION['cesta'][$codigo]++;
                                    } else {
                                        $_SESSION['cesta'] += [$codigo => $cantidad];
                                    }
                                    $_SESSION['cesta'][$valor] = $_SESSION['cesta'][$valor]++;  //Si el producto esta en la cesta, pues se suma la cantidad
                                    if ($_SESSION['prods'][$i]["STOCK"] > 0) {
                                        $_SESSION['prods'][$i]["STOCK"]--;
                                    }
                                }
                            }
                        }
                    }
                }
        echo "<form action='index.php'>&nbsp;&nbsp;&nbsp;<button name='cerrar' id='cerrar' type='submit' value='cerrar'>Cerrar Sesion</button></form><br>";
        if (isset($_SESSION['prods']))
            if (isset($_SESSION['contador'])) {
                echo "En cesta: <b>" . $_SESSION['contador'] . "</b> productos."; /*sizeof($_SESSION['cesta'])*/
                print_r($_SESSION['cesta']);
            } else {
                echo "En cesta: <b>0</b> productos."; /*sizeof($_SESSION['cesta'])*/
                print_r($_SESSION['cesta']);
            }

        }
        echo "<h2>TodoInformatica</h2>";
        echo "<table>
             <tr>
                <th>COD</th>
                <th>NOMBRE</th>
                <th>PVP</th>
                <th>CANTIDAD</th>
             </tr>";
        $atribot = "";
        $datot = "";
        foreach($_SESSION['prods'] as $prods) {
            echo "<tr>";
            echo "<form action='productos.php' method='post'>";
            foreach ($prods as $atrib => $dato) {
                echo "<td>$dato</td>";
                echo "<input type='hidden' name='$atrib' value='$dato'/>";
                if ($atrib == "COD") {
                    $datot = $dato;
                }
            }
            echo "<td><button type='submit' name='COD' value='$datot'>Elegir</button>";    //Mediante esto, le paso el atributo COD y el valor del codigo
            echo "</form>";
            echo "</tr>";
        }
        echo "</table>";

    echo "<form action='cesta.php' method='post'>";
    echo "<br><select name='prod' id='prod'>";
    foreach($_SESSION['prods'] as $prods) {
        echo "<option>";
        foreach ($prods as $atrib => $dato) {
            if ($atrib == "PVP") {
                echo $dato . "€ ";
            } else if ($atrib == "STOCK") {
                echo " | Quedan " . $dato;
            } else {
                echo $dato . " || ";
            }
        }
    }//Termino escribir HTML
        echo "</option>";
        echo "</select>";
        echo"<form action='cesta.php' method='post'>";  //Form con botones, el primero WIP; el segundo manda al usuario a CESTA.PHP
        //echo "&nbsp;  <button type='button' id='elegir' name='elegir'>Añadir a la cesta</button>";
        echo"&nbsp;<button type='submit' id='comp' name='comp'>Comprar<img style='width: 55px; height: 55px;' src='https://cdn-icons-png.flaticon.com/128/8567/8567737.png' title='checkout'/></button>";
        echo"</form>";
        echo "<form action='productos.php' method='post'>"; //Form con botón para VACIAR el carrito  [UNSET a $_SESSION['cesta']]
        echo "<button type='submit' value='vaciar' name='vaciar'>VACIAR CARRITO</button>";
        echo "</form>";
        echo"</body>";
        echo"</html>";
    } else {    //Aqui podremos proceder, dependiendo del mensaje de error que enviemos con los POST
        if ($_REQUEST['error'] == 'NO HAY PRODUCTOS') {
            unset($_REQUEST['error']);
            header('Location: productos.php');
        } else {
            $_SESSION['cerrar'] = true;
            session_destroy();
            header('Location: index.php');
        }

    }
?>
