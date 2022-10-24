<?php
// Recuperamos la información de la sesión
session_start();
// Y comprobamos que el usuario se haya autentificado
if (!isset($_SESSION['usu'])) {
    die("Error - debe <a href='index.php'>identificarse</a>.<br />");
}
echo "Usuario/a: <b>" . $_SESSION['usu'] . "</b>";
echo "<form action='index.php'>&nbsp;&nbsp;&nbsp;<button name='cerrar' id='cerrar' type='submit' value='cerrar'>Cerrar Sesion</button></form><br>";
?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <!-- Desarrollo Web en Entorno Servidor -->
    <!-- Tema 4 : Desarrollo de aplicaciones web con PHP -->
    <!-- Ejemplo Tienda Web: cesta.php -->
    <html lang="es">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Ejemplo Tema 4: Cesta de la Compra</title>
        <link href="tienda.css" rel="stylesheet" type="text/css">
    </head>
    <body class="pagcesta">
    <div id="contenedor">
        <div id="encabezado">
            <h1>Cesta de la compra</h1>
        </div>
        <div id="productos">
            <?php
            $totalIVA = 0;
            $total = 0;
            $nombre = "";
            $i = -1;
            if(!empty($_SESSION['cesta'])) {    //Chequea si la cesta está vacía
                foreach($_SESSION['cesta'] as $codigo => $cantidad) {
                    $i++;
                    echo "<p><span class='codigo'>$codigo</span>";
                    foreach ($_SESSION['prods'] as $prod) {
                        foreach ($prod as $clave => $valor) {
                            if($valor == $codigo) {
                                $nombre = $prod["NOMBRE"];
                                $precio = $prod["PVP"]*$cantidad;
                            }
                        }
                    }
                    echo "<span class='nombre'>" . $nombre . "</span>";
                    echo "<span class='precio'>" . $precio . "</span></p>";
                    $IVA = 0.21;
                    $totalIVA += $precio*0.21;
                    $total += $totalIVA+$precio;
                }

            echo "<hr/>";
            echo "<p><span class='pagar'>IVA (21%):<span class='precioIVA'>" . $totalIVA . "€" . "</span></span></p>";
            echo "<p><span class='pagar'>Precio total:<span class='precioTotal'>" . $total . "€" . "</span></span></p>";

            echo "<form action='pagar.php' method='post'>";
                echo "<p>";
        echo "<span class='pagar'>
        <input type='submit' name='pagar' value='pagar'/>
        </span>";
                print "<br><form action='productos.php' method='post'>";
                print "<br><button type='submit' name='Volver' value='atras'>&lt;-Volver a la tienda</button>";    //Name devuelve una clave, y value se asemeja al valor de esa clave
                print "</form>";
                echo "</p>";
            echo "</form>";
            } else {
                print "NO SE HAN INTRODUCIDO PRODUCTOS";
                print "<br><form action='productos.php' method='post'>";
                print "<button type='submit' name='error' value='NO HAY PRODUCTOS'>&lt;-Volver a tienda</button>";    //Name devuelve una clave, y value se asemeja al valor de esa clave
                print "</form>";
            }

            ?>
        </div>
        <br class="divisor" />
        <div id="pie">
            <form action='index.php' method='post'>
                <input style="padding:5px;margin-top:5px;" type='submit' name='cerrar' value="cerrar"/>
            </form>
        </div>
    </div>
    </body>
    </html>
<?php
