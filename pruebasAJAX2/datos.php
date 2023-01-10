<?php
require_once "conexionBD.php";

if (isset($_REQUEST['nombre']) && isset($_REQUEST['password']) && $_REQUEST['nombre'] != "" && $_REQUEST['password'] != "") {
    $cnx = conexionBD::getConexion();
    $sql = "SELECT * FROM usuarios ORDER BY 1";

    $stmt = $cnx->query($sql);
    if ($stmt->rowCount() > 0) {
        while ($arr = $stmt->fetch(PDO::FETCH_ASSOC)) {
            if ($arr['nombre'] == $_REQUEST['usuario'] && $arr['password'] == $_REQUEST['password']) {

            }
        }
    }
}



//SABER USAR

//  PHP                 JS                  El archivo JSON...
//  json_encode() -->   JSON.parse()        //viene de PHP - a JS
//  json_decode() <--   JSON.stringify()    //viene de JS - a PHP